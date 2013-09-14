package foo.dbgroup.spark;

import static spark.Spark.get;
import static spark.Spark.setPort;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;

import org.bson.types.ObjectId;

import spark.Request;
import spark.Response;
import spark.Route;

import com.google.code.morphia.Morphia;
import com.google.code.morphia.logging.MorphiaLoggerFactory;
import com.google.code.morphia.logging.slf4j.SLF4JLogrImplFactory;
import com.mongodb.DB;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

import foo.dbgroup.mongo.dao.DatasetResultDAO;
import foo.dbgroup.mongo.dao.EndpointSparqlDAO;
import foo.dbgroup.mongo.dao.GenericQueryDAO;
import foo.dbgroup.mongo.entity.DatasetResult;
import foo.dbgroup.mongo.entity.EndPointSparql;
import freemarker.template.Configuration;
import freemarker.template.SimpleHash;
import freemarker.template.Template;
import freemarker.template.TemplateException;



public class LunchSite {

	
	private final Configuration cfg;
	private final DatasetResultDAO daDao;
	private final EndpointSparqlDAO endDao;
	private final GenericQueryDAO queryDao;
	
	
	
	public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            new LunchSite("mongodb://localhost");
        }
        else {
            new LunchSite(args[0]);
        }
    }
	
	
	public LunchSite(String mongoURIString) throws IOException {
//        final MongoClient mongoClient = new MongoClient(new MongoClientURI(mongoURIString));
//        final DB blogDatabase = mongoClient.getDB("blog");
		MorphiaLoggerFactory.registerLogger(SLF4JLogrImplFactory.class);
		Mongo mongo= null;
		try {
			mongo = new Mongo();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Morphia morphia = new Morphia();
		
		daDao=new DatasetResultDAO(mongo,morphia);
		endDao= new EndpointSparqlDAO(mongo, morphia);
		queryDao= new GenericQueryDAO(mongo, morphia);
		
//        userDAO = new UserDAO(blogDatabase);
//        sessionDAO = new SessionDAO(blogDatabase);

        cfg = createFreemarkerConfiguration();
        setPort(8082);
        initializeRoutes();
    }
	
	abstract class FreemarkerBasedRoute extends Route {
        final Template template;

        /**
         * Constructor
         *
         * @param path The route path which is used for matching. (e.g. /hello, users/:name)
         */
        protected FreemarkerBasedRoute(final String path, final String templateName) throws IOException {
            super(path);
            template = cfg.getTemplate(templateName);
        }

        @Override
        public Object handle(Request request, Response response) {
            StringWriter writer = new StringWriter();
            try {
                doHandle(request, response, writer);
            } catch (Exception e) {
                e.printStackTrace();
                response.redirect("/internal_error");
            }
            return writer;
        }

        protected abstract void doHandle(final Request request, final Response response, final Writer writer)
                throws IOException, TemplateException;

    }
	
	
	private void initializeRoutes() throws IOException {
        // this is the blog home page
        get(new FreemarkerBasedRoute("/","index.ftl"){

			@Override
			protected void doHandle(Request request, Response response,
					Writer writer) throws IOException, TemplateException {
				

				List<EndPointSparql> datasets=endDao.find().asList();
				SimpleHash root = new SimpleHash();

                root.put("datasets",datasets);
                
                template.process(root, writer);
				
			}
        	
        });
        
        get(new FreemarkerBasedRoute("/internal_error", "error_template.ftl") {
            @Override
            protected void doHandle(Request request, Response response, Writer writer) throws IOException, TemplateException {
                SimpleHash root = new SimpleHash();

                root.put("error", "System has encountered an error.");
                template.process(root, writer);
            }
        });
        
        
        get(new FreemarkerBasedRoute("/result/:id","result.ftl"){

			@Override
			protected void doHandle(Request request, Response response,
					Writer writer) throws IOException, TemplateException {
				String id = request.params(":id");
				
				Integer o=new Integer(Integer.parseInt(id));
				EndPointSparql end=endDao.findOne("id", o);
				
				DatasetResult d=daDao.findOne("uri", end.getUri());
				SimpleHash root = new SimpleHash();
				root.put("queryResult", d.getQueryResult());
//				root.put("queryResult", d.getQueryResultNoSchema());
				root.put("name", d.getName());
				root.put("uri", d.getUri());
				template.process(root, writer);
			}
        	
        });
        		
        		
        		
	}
	
	
	
    private Configuration createFreemarkerConfiguration() {
        Configuration retVal = new Configuration();
        retVal.setClassForTemplateLoading(LunchSite.class, "/freemarker");
        return retVal;
    }
	
	
}
