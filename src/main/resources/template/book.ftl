<html>
    <head>
        <title>Book Detail</title>
        <script type="text/javascript">
            function sendPutRequest() {
                var bookId = document.getElementById("id").value;
                var newAuthor = document.getElementById("author").value;
                var newTitle = document.getElementById("title").value;
                var bookParameter = bookId + '?author=' + newAuthor + '&title=' + newTitle;
                
                var xhrReq=new XMLHttpRequest();
                xhrReq.onreadystatechange=function()
                {
                  if (xhrReq.readyState==4 && xhrReq.status>=200 && xhrReq.status<300)
                  {
                    document.getElementById('returnMessage').innerHTML=xhrReq.responseText;
                  }
                }
                xhrReq.open('PUT', '/books/' + bookParameter, false);
                xhrReq.send();
            }

            function sendDeleteRequest() {
                var bookId = document.getElementById("id").value;
                var xhrReq=new XMLHttpRequest();
                xhrReq.onreadystatechange=function()
                {
                  if (xhrReq.readyState==4 && xhrReq.status>=200 && xhrReq.status<300)
                  {
                    document.getElementById('returnMessage').innerHTML=xhrReq.responseText;
                    var delay = 5;
                    document.getElementById('countBoard').innerHTML= delay + ' secs to back to bookshlef!';
                    setTimeout('backToShelf()', delay * 1000);
                  }
                }
                xhrReq.open('DELETE', '/books/' + bookId, false);
                xhrReq.send();
            }
            
            function backToShelf() {
                alert('Now back to bookshelf');
                window.location='/books';
            }
        </script> 
    </head>
    <body>
        <p id='returnMessage'></p>
        <p id='countBoard'></p>
        <p>Create Date: ${book.date}</p>
        <p>BookId: <input type="text" name="id" id="id" value="${book.id}" readonly /></p>
        <p>Author: <input type="text" name="author" id="author" value="${book.author}"/></p>
        <p>Title: <input type="text" name="title" id="title" value="${book.title}"/></p>
        <input type="button" value="Update Book" onclick="sendPutRequest()"/>
        <input type="button" value="Delete Book" onclick="sendDeleteRequest()"/>
        <a href="/books" >Back to bookshelf</a>
    </body>
</html>