function appendLivresIsbn() {

    $('#livres-display-div').empty(); // Flush it first

    const isbnLivreSearch = $("#isbnLivre").val();
    const urlLink = "http://localhost:8080/livres/" + isbnLivreSearch;
    
    console.log("Loading livre with URL " + urlLink);

    $.ajax({
        method: "GET", // HTTP method
        url: urlLink
      })
      .done(function(data) {
        console.log(data);
        var book = data;
        
        if(book === null || book === undefined) {
            $('#livres-display-div').text("Pas de livre trouvé !");
        } else {

            const contentDisplay = $('#livres-display-div');

            const newLivreTable = $("<table>")
                    .addClass("table table-striped")
                    .attr("id", "livre-table");

            const thead = $("<thead>");
            const tr = $("<tr>");
            const th1 = $("<th>").text("ISBN Livre")
                        .attr("scope", "col");                    
            const th2 = $("<th>").text("Titre livre")
                        .attr("scope", "col");
            const th3 = $("<th>").text("Auteur")
                        .attr("scope", "col");
            const th4 = $("<th>").text("Editeur")
                        .attr("scope", "col");
            const th5 = $("<th>").text("Nombre de pages")
                        .attr("scope", "col");

            th1.appendTo(tr);
            th2.appendTo(tr);
            th3.appendTo(tr);
            th4.appendTo(tr);
            th5.appendTo(tr);
            tr.appendTo(thead);

            const tbody = $("<tbody>");
            const tr2 = $("<tr>");
            var thbook1 = $("<th>")
                        .text(book.isbnLivre)
                        .attr("scope", "row");
            var tdbook1 = $("<td>")
                        .text(book.titre);
            var tdbook2 = $("<td>")
                        .text(book.auteur.nomAu + " " + book.auteur.prenomAu);                 
            var tdbook3 = $("<td>")
                        .text(book.editeur);             
            var tdbook4 = $("<td>")
                        .text(book.nbrePages); 
            
            thbook1.appendTo(tr2);
            tdbook1.appendTo(tr2);
            tdbook2.appendTo(tr2);
            tdbook3.appendTo(tr2);
            tdbook4.appendTo(tr2);
            tr2.appendTo(tbody);
            
            thead.appendTo(newLivreTable);
            tbody.appendTo(newLivreTable);
            newLivreTable.appendTo(contentDisplay);
        }
        })
    .fail(function(jqXHR, textStatus) {
        console.log("Issue with the Ajax call !");
        console.log(jqXHR);
        console.log(textStatus);
    });
}


function appendLivresTitre() {

    $('#livres-display-div').empty(); // Flush it first

    const titreLivreSearch = $("#titreLivre").val();
    const urlLink = "http://localhost:8080/livres/searchTitle";
    
    $.ajax({
        method: "GET", // HTTP method
        url: urlLink,
        data: {title: titreLivreSearch}, // Data to send to the server (optional)
        dataType: "json" // Expected data type from the server (optional)
      })
      .done(function(data) {
        console.log(data);

        if(data.length > 0) {
            const contentDisplay = $('#livres-display-div');

            const newLivreTable = $("<table>")
            .addClass("table table-striped")
            .attr("id", "livre-table");

            const thead = $("<thead>");
            const tr = $("<tr>");
            const th1 = $("<th>").text("ISBN Livre")
                        .attr("scope", "col");                    
            const th2 = $("<th>").text("Titre livre")
                        .attr("scope", "col");
            const th3 = $("<th>").text("Auteur")
                        .attr("scope", "col");
            const th4 = $("<th>").text("Editeur")
                        .attr("scope", "col");
            const th5 = $("<th>").text("Nombre de pages")
                        .attr("scope", "col");

            th1.appendTo(tr);
            th2.appendTo(tr);
            th3.appendTo(tr);
            th4.appendTo(tr);
            th5.appendTo(tr);
            tr.appendTo(thead);
                
            const tbody = $("<tbody>");

            data.forEach(function(book) {
                var tr2 = $("<tr>");
                var thbook1 = $("<th>")
                            .text(book.isbnLivre)
                            .attr("scope", "row");
                var tdbook1 = $("<td>")
                            .text(book.titre);
                var tdbook2 = $("<td>")
                            .text(book.auteur.nomAu + " " + book.auteur.prenomAu);                 
                var tdbook3 = $("<td>")
                            .text(book.editeur);             
                var tdbook4 = $("<td>")
                            .text(book.nbrePages); 
                
                thbook1.appendTo(tr2);
                tdbook1.appendTo(tr2);
                tdbook2.appendTo(tr2);
                tdbook3.appendTo(tr2);
                tdbook4.appendTo(tr2);
                tr2.appendTo(tbody);
            });
        
            thead.appendTo(newLivreTable);
            tbody.appendTo(newLivreTable);
            newLivreTable.appendTo(contentDisplay);
        } else {
            $('#livres-display-div').text("Pas de livre trouvé !");
        }
      })
      .fail(function(jqXHR, textStatus) {
        console.log("Issue with the Ajax call !");
        console.log(jqXHR);
        console.log(textStatus);
      });

}


function appendLivresAuteur() {

    $('#livres-display-div').empty(); // Flush it first

    const prenomAu = $("#prenomAu").val();
    const nomAu = $("#nomAu").val();
    const urlLink = "http://localhost:8080/livres/searchAuteur";
    
    $.ajax({
        method: "GET", // HTTP method
        url: urlLink,
        data: {firstName: prenomAu, lastName: nomAu}, // Data to send to the server (optional)
        dataType: "json" // Expected data type from the server (optional)
      })
      .done(function(data) {
        console.log(data);

        if(data.length > 0) {
            const contentDisplay = $('#livres-display-div');

            const newLivreTable = $("<table>")
            .addClass("table table-striped")
            .attr("id", "livre-table");

            const thead = $("<thead>");
            const tr = $("<tr>");
            const th1 = $("<th>").text("ISBN Livre")
                        .attr("scope", "col");                    
            const th2 = $("<th>").text("Titre livre")
                        .attr("scope", "col");
            const th3 = $("<th>").text("Auteur")
                        .attr("scope", "col");
            const th4 = $("<th>").text("Editeur")
                        .attr("scope", "col");
            const th5 = $("<th>").text("Nombre de pages")
                        .attr("scope", "col");

            th1.appendTo(tr);
            th2.appendTo(tr);
            th3.appendTo(tr);
            th4.appendTo(tr);
            th5.appendTo(tr);
            tr.appendTo(thead);
                
            const tbody = $("<tbody>");

            data.forEach(function(book) {
                var tr2 = $("<tr>");
                var thbook1 = $("<th>")
                            .text(book.isbnLivre)
                            .attr("scope", "row");
                var tdbook1 = $("<td>")
                            .text(book.titre);
                var tdbook2 = $("<td>")
                            .text(book.auteur.nomAu + " " + book.auteur.prenomAu);                 
                var tdbook3 = $("<td>")
                            .text(book.editeur);             
                var tdbook4 = $("<td>")
                            .text(book.nbrePages); 
                
                thbook1.appendTo(tr2);
                tdbook1.appendTo(tr2);
                tdbook2.appendTo(tr2);
                tdbook3.appendTo(tr2);
                tdbook4.appendTo(tr2);
                tr2.appendTo(tbody);
            });
        
            thead.appendTo(newLivreTable);
            tbody.appendTo(newLivreTable);
            newLivreTable.appendTo(contentDisplay);
        } else {
            $('#livres-display-div').text("Pas de livre trouvé !");
        }
      })
      .fail(function(jqXHR, textStatus) {
        console.log("Issue with the Ajax call !");
        console.log(jqXHR);
        console.log(textStatus);
      });

}