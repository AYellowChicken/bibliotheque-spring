var id = 1;

function loadPerson() {
    const url = "https://reqres.in/api/users?page=" + id;
    id++;
    console.log("Entered the load function");

    $.get(url, function(responseJson, status) {
        
        if (status === "success") {
            const contentDisplay = $("#persons-display-div");

            $.each(responseJson.data, function(i, person) {
                const newPersonDiv = $("<div>")
                    .addClass("person")
                    .attr("id", "person" + person.id);

                const container = $("<div>")
                    .addClass("container");

                const row = $("<div>")
                    .addClass("row");

                const col1 = $("<div>")
                    .addClass("col-sm");

                const col2 = $("<div>")
                    .addClass("col-sm");

                $("<p>")
                    .addClass("text")
                    .text("Prenom : " + person.first_name)
                    .appendTo(col1);

                $("<p>")
                    .addClass("text")
                    .text("Nom : " + person.last_name)
                    .appendTo(col1);

                $("<p>")
                    .addClass("text")
                    .text("Email : " + person.email)
                    .appendTo(col1);

                $("<img>")
                    .attr("src", person.avatar)
                    .appendTo(col2);

                col1.appendTo(row);
                col2.appendTo(row);
                row.appendTo(container);
                container.appendTo(newPersonDiv);
                newPersonDiv.appendTo(contentDisplay);
            });
        } else {
            // Oh no! There has been an error with the request!
        }
    });
}

function appendBooks() {
    // Assume you have a JSON response with an array of books
    const books = [
    {id: 1, title: "The Catcher in the Rye", author: "J.D. Salinger"},
    {id: 2, title: "To Kill a Mockingbird", author: "Harper Lee"},
    {id: 3, title: "1984", author: "George Orwell"}
  ];
  
  // Iterate through the books array and add each book to the table
  const tableBody = $("#books-table tbody");
  books.forEach(book => {
    const row = $("<tr>");
    $('<th scope="row">').text(book.id).appendTo(row);
    // $("<td>").text(book.id).appendTo(row);
    $("<td>").text(book.title).appendTo(row);
    $("<td>").text(book.author).appendTo(row);
    // Add more columns if needed
    row.appendTo(tableBody);
  });
  
}

// Call the loadPerson function when the button is clicked
$(document).ready(function() {
    $("#load-person-button").click(loadPerson);
    appendBooks();
});

// $(document).delegate("#load-person-button", "click", loadPerson);