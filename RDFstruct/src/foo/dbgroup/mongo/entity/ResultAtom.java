package foo.dbgroup.mongo.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Embedded;
import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;


public class ResultAtom {


	
	private String title;
	
	private Integer queryNumber;
	
	private Integer count;
	
	private List<String> result;
	
	private String error;
	
	private Date time;
	
	@Embedded
	private
	List<DoubleResult> cresult;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}



	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public List<String> getResult() {
		return result;
	}

	public void setResult(List<String> result) {
		this.result = result;
	}
	
	public void addResult(String element) {
		if(result==null)result=new ArrayList<String>();
		result.add(element);
	}

	public List<DoubleResult> getCresult() {
		return cresult;
	}

	public void setCresult(List<DoubleResult> cresult) {
		this.cresult = cresult;
	}
	
	public void addCresult(DoubleResult element) {
		if(cresult == null) cresult=new ArrayList<DoubleResult>();
		cresult.add(element);
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public Integer getQueryNumber() {
		return queryNumber;
	}

	public void setQueryNumber(Integer queryNumber) {
		this.queryNumber = queryNumber;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}
	
	
}
