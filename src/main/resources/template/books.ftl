<html>
    <head>
      <title>Spark Bookshelf!</title>
    </head>
    <body>
        <h1>Welcome to Spark Bookshelf!</h1>
        <table border="1">
            <thead>
                <tr>
                    <th>Author</th>
                    <th>Title</th>
                    <th>Operation</th>
                </tr>
            </thead>
            <tbody>
                <#list books as book>
                    <tr id="${book.id}" >
                        <td>${book.author}</td>
                        <td>${book.title}</td>
                        <td><a href="/books/${book.id}" >Detail</a></td>
                    </tr>
                </#list>
            </tbody>
        </table>
        <form id="newBook" name="newBookForm" method="post" action="/books">
            <table>
                <tbody>
                    <tr>
                        <td>Author</td>
                        <td><input type="text" name="author" /></td>
                    </tr>
                    <tr>
                        <td>Title</td>
                        <td><input type="text" name="title" /></td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="New Book" />
        </form>
    </body>
</html>