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
    <h1>Welcome RDF Struct</h1>
    <p>
    <h2>Dataset : </h2>
<p>


<#list datasets as dataset>
    <h2><a href="/result/${dataset["id"]}">${dataset["nome"]}</a></h2>
    Uri : ${dataset["uri"]} 
</#list>


  </body>

</html>
