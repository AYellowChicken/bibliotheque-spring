function appendEmpruntsNumAbonne() {

    $('#emprunts-display-div').empty(); // Flush it first

    const numAbonne = $("#numAbonne").val();
    const urlLink = "http://localhost:8080/emprunts/" + numAbonne;
    
    $.ajax({
        method: "GET", // HTTP method
        url: urlLink,
        dataType: "json" // Expected data type from the server (optional)
      })
      .done(function(data) {
        console.log(data);

        if(data.length > 0) {
            const contentDisplay = $('#emprunts-display-div');

            const newEmpruntTable = $("<table>")
            .addClass("table table-striped")
            .attr("id", "emprunt-table");

            const thead = $("<thead>");
            const tr = $("<tr>");
            const th1 = $("<th>").text("Abonne")
                        .attr("scope", "col");                    
            const th2 = $("<th>").text("Livre")
                        .attr("scope", "col");
            const th3 = $("<th>").text("Date emprunt")
                        .attr("scope", "col");
            const th4 = $("<th>").text("Date retour prévue")
                        .attr("scope", "col");
            const th5 = $("<th>").text("Date retour effective")
                        .attr("scope", "col");            
            const th6 = $("<th>").text("Emprunt fautif")
                        .attr("scope", "col");

            th1.appendTo(tr);
            th2.appendTo(tr);
            th3.appendTo(tr);
            th4.appendTo(tr);
            th5.appendTo(tr);
            th6.appendTo(tr);
            tr.appendTo(thead);
                
            const tbody = $("<tbody>");

            data.forEach(function(emprunt) {
                var tr2 = $("<tr>");
                var themprunt1 = $("<th>")
                            .text(emprunt.abonne.prenomAb + " " + emprunt.abonne.nomAb + " (" + emprunt.abonne.numAbonne + ")")
                            .attr("scope", "row");
                var tdemprunt1 = $("<td>")
                            .text(emprunt.livre.titre + " (" + emprunt.livre.isbnLivre + ")");
                var tdemprunt2 = $("<td>")
                            .text(emprunt.dateEmprunt);                 
                var tdemprunt3 = $("<td>")
                            .text(emprunt.dateRetourPrevue);             
                var tdemprunt4 = $("<td>")
                            .text(emprunt.dateRetourEffective);                
                var tdemprunt5 = $("<td>")
                            .text(emprunt.retardNY);
                
                themprunt1.appendTo(tr2);
                tdemprunt1.appendTo(tr2);
                tdemprunt2.appendTo(tr2);
                tdemprunt3.appendTo(tr2);
                tdemprunt4.appendTo(tr2);
                tdemprunt5.appendTo(tr2);
                tr2.appendTo(tbody);
            });
        
            thead.appendTo(newEmpruntTable);
            tbody.appendTo(newEmpruntTable);
            newEmpruntTable.appendTo(contentDisplay);
        } else {
            $('#livres-display-div').text("Pas d'emprunt trouvé !");
        }
      })
      .fail(function(jqXHR, textStatus) {
        console.log("Issue with the Ajax call !");
        console.log(jqXHR);
        console.log(textStatus);
      });

}