# VoyagerSearch_SaisumanthGopisetty

Please read this file before running the code on Server.

I have plugged some sample data to show results for my queries. So, before running the code please follow the following steps to have data on your machine as well. Run the following commands to populate the data.

1. Install Solr server and start it.
2. Create a document.
solr create -c demo  
3. Add some sample data.
curl http://localhost:8983/solr/demo/update?commitWithin=5000 -H 'Content-type:text/csv' -d '
id,cat_s,pubyear_i,title_t,author_s,series_s,sequence_i,publisher_s
book1,fantasy,2010,The Way of Kings,Brandon Sanderson,The Stormlight Archive,1,Tor
book2,fantasy,1996,A Game of Thrones,George R.R. Martin,A Song of Ice and Fire,1,Bantam
book3,fantasy,1999,A Clash of Kings,George R.R. Martin,A Song of Ice and Fire,2,Bantam
book4,sci-fi,1951,Foundation,Isaac Asimov,Foundation Series,1,Bantam
book5,sci-fi,1952,Foundation and Empire,Isaac Asimov,Foundation Series,2,Bantam
book6,sci-fi,1992,Snow Crash,Neal Stephenson,Snow Crash,,Bantam
book7,sci-fi,1984,Neuromancer,William Gibson,Sprawl trilogy,1,Ace
book8,fantasy,1985,The Black Company,Glen Cook,The Black Company,1,Tor
book9,fantasy,1965,The Black Cauldron,Lloyd Alexander,The Chronicles of Prydain,2,Square Fish
book10,fantasy,2001,American Gods,Neil Gaiman,,,Harper'


Now you are all set to run the queries. To demonstrate the requirements, I have written 3 Rest APIs.
1. API:1: http://localhost:8086//VoyagerSearchDemo/webapi/myresource/id/{id}
 This is a simple query to fetch a record from the sample data.

 Ex:http://localhost:8086/VoyagerSearchDemo/webapi/myresource/id/book1
 The output would be:
{
  "doc":
  {
    "id":"book1",
    "cat_s":"fantasy",
    "pubyear_i":2010,
    "title_t":["The Way of Kings"],
    "author_s":"Brandon Sanderson",
    "series_s":"The Stormlight Archive",
    "sequence_i":1,
    "publisher_s":"Tor",
    "_version_":1565350390511173632}}

 Plug any id after id/ to fetch the corresponding record.

2. API 2: http://localhost:8086//VoyagerSearchDemo/webapi/myresource/query/{queryparam}/filters/{returntype1}/{returntype2}  
  This API is used if you want to define your return type of items. For instance, if you want only title_t and author_s in your results, you can use this.
  Ex: http://localhost:8086/VoyagerSearchDemo/webapi/myresource/query/title_t:black/filters/title_t/author_s
 This API will return only fields title_t and author_s for all the records whose title_t has string ‘black’.
 The output would be:
{
  "responseHeader":{
    "status":0,
    "QTime":6,
    "params":{
      "q":"title_t:black",
      "fl":"title_t,author_s"}},
  "response":{"numFound":2,"start":0,"docs":[
      {
        "title_t":["The Black Company"],
        "author_s":"Glen Cook"},
      {
        "title_t":["The Black Cauldron"],
        "author_s":"Lloyd Alexander"}]
  }}

3.API 3: http://localhost:8086//VoyagerSearchDemo/webapi/myresource/deepquery/publisher/{authorname}/rows/{numberofresults}/sortparam/{sortparameter}/sortorder/{sortorder}
 This API is used for advanced operations like Sorting and paging.
 Ex: http://localhost:8086/VoyagerSearchDemo/webapi/myresource/deepquery/publisher/Bantam/rows/3/sortparam/pubyear_i/sortorder/Desc
 The above query will return all the results which are written by Bantam. It gives only 3 results. The results are sorted based on pubyear_i in Descending order
 The output would be:
{
  "responseHeader":{
    "status":0,
    "QTime":21,
    "params":{
      "q":"*:*",
      "fq":"publisher_s:Bantam",
      "sort":"pubyear_i Desc",
      "rows":"3"}},
  "response":{"numFound":5,"start":0,"docs":[
      {
        "id":"book3",
        "cat_s":"fantasy",
        "pubyear_i":1999,
        "title_t":["A Clash of Kings"],
        "author_s":"George R.R. Martin",
        "series_s":"A Song of Ice and Fire",
        "sequence_i":2,
        "publisher_s":"Bantam",
        "_version_":1565350390519562240},
      {
        "id":"book2",
        "cat_s":"fantasy",
        "pubyear_i":1996,
        "title_t":["A Game of Thrones"],
        "author_s":"George R.R. Martin",
        "series_s":"A Song of Ice and Fire",
        "sequence_i":1,
        "publisher_s":"Bantam",
        "_version_":1565350390517465088},
      {
        "id":"book6",
        "cat_s":"sci-fi",
        "pubyear_i":1992,
        "title_t":["Snow Crash"],
        "author_s":"Neal Stephenson",
        "series_s":"Snow Crash",
        "publisher_s":"Bantam",
        "_version_":1565350390524805120}]
  }}
 



