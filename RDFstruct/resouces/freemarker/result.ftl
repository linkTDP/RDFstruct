<!DOCTYPE html>

<html>
  <head>
    <title>Welcome RDF struct</title>
    <style type="text/css">
      .label {text-align: right}
      .error {color: red}
    </style>

  </head>

  <body>
  <h1>${name}  - ${uri}  </h1>
  <p>
  
 <#list queryResult as res>
    <h2>${res["title"]}</h2>
    ${res["count"]!}
    
    
    <#if res.result??>
    	<#list res.result as lista>
    		${lista}<br>	
		</#list>    	
    </#if>
    
    <#if res.cresult??>
    	<#list res.cresult as clista>
    		${clista["prop"]}  --  ${clista["count"]}<br>	
		</#list>    	
    </#if>
    
    ${res["error"]!}
    

    	
</#list>
   
  
  </body>
  </html>