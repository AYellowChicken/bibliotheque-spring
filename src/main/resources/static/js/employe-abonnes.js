function appendAbonnesNumAbonne() {

    $('#abonnes-display-div').empty(); // Flush it first

    const numAbonneSearch = $("#numAbonne").val();
    const urlLink = "http://localhost:8080/abonnes/" + numAbonneSearch;
    
    console.log("Loading abonné with URL " + urlLink);

    $.ajax({
        method: "GET", // HTTP method
        url: urlLink
      })
      .done(function(data) {
        console.log(data);
        var abonne = data;
        
        if(abonne === null || abonne === undefined) {
            $('#abonnes-display-div').text("Pas d'abonné trouvé !");
        } else {

            const contentDisplay = $('#abonnes-display-div');

            const newAbonneTable = $("<table>")
                    .addClass("table table-striped")
                    .attr("id", "livre-table");

            const thead = $("<thead>");
            const tr = $("<tr>");
            const th1 = $("<th>").text("Numéro abonné")
                        .attr("scope", "col");                    
            const th2 = $("<th>").text("Nom")
                        .attr("scope", "col");
            const th3 = $("<th>").text("Prénom")
                        .attr("scope", "col");
            const th4 = $("<th>").text("Adresse")
                        .attr("scope", "col");
            const th5 = $("<th>").text("Téléphone")
                        .attr("scope", "col");

            th1.appendTo(tr);
            th2.appendTo(tr);
            th3.appendTo(tr);
            th4.appendTo(tr);
            th5.appendTo(tr);
            tr.appendTo(thead);

            const tbody = $("<tbody>");
            const tr2 = $("<tr>");
            var thabonne1 = $("<th>")
                        .text(abonne.numAbonne)
                        .attr("scope", "row");
            var tdabonne1 = $("<td>")
                        .text(abonne.nomAb);
            var tdabonne2 = $("<td>")
                        .text(abonne.prenomAb);                 
            var tdabonne3 = $("<td>")
                        .text(abonne.adresseAb);             
            var tdabonne4 = $("<td>")
                        .text(abonne.telephoneAb); 
            
            thabonne1.appendTo(tr2);
            tdabonne1.appendTo(tr2);
            tdabonne2.appendTo(tr2);
            tdabonne3.appendTo(tr2);
            tdabonne4.appendTo(tr2);
            tr2.appendTo(tbody);
            
            thead.appendTo(newAbonneTable);
            tbody.appendTo(newAbonneTable);
            newAbonneTable.appendTo(contentDisplay);
        }
        })
    .fail(function(jqXHR, textStatus) {
        console.log("Issue with the Ajax call !");
        console.log(jqXHR);
        console.log(textStatus);
    });
}


function appendAbonnesNomPrenom() {

    $('#abonnes-display-div').empty(); // Flush it first

    const abonnePrenomSearch = $("#prenomAb").val();
    const abonneNomSearch = $("#nomAb").val();
    const urlLink = "http://localhost:8080/abonnes/searchName";
    
    $.ajax({
        method: "GET", // HTTP method
        url: urlLink,
        data: {prenomAb: abonnePrenomSearch, nomAb: abonneNomSearch}, // Data to send to the server (optional)
        dataType: "json" // Expected data type from the server (optional)
      })
      .done(function(data) {
        console.log(data);

        if(data !== undefined && data.length > 0) {
            const contentDisplay = $('#abonnes-display-div');

            const newAbonneTable = $("<table>")
                    .addClass("table table-striped")
                    .attr("id", "livre-table");

            const thead = $("<thead>");
            const tr = $("<tr>");
            const th1 = $("<th>").text("Numéro abonné")
                        .attr("scope", "col");                    
            const th2 = $("<th>").text("Nom")
                        .attr("scope", "col");
            const th3 = $("<th>").text("Prénom")
                        .attr("scope", "col");
            const th4 = $("<th>").text("Adresse")
                        .attr("scope", "col");
            const th5 = $("<th>").text("Téléphone")
                        .attr("scope", "col");
                        
            th1.appendTo(tr);
            th2.appendTo(tr);
            th3.appendTo(tr);
            th4.appendTo(tr);
            th5.appendTo(tr);
            tr.appendTo(thead);
                
            const tbody = $("<tbody>");

            data.forEach(function(abonne) {
                var tr2 = $("<tr>");
                var thabonne1 = $("<th>")
                            .text(abonne.numAbonne)
                            .attr("scope", "row");
                var tdabonne1 = $("<td>")
                            .text(abonne.nomAb);
                var tdabonne2 = $("<td>")
                            .text(abonne.prenomAb);                 
                var tdabonne3 = $("<td>")
                            .text(abonne.adresseAb);             
                var tdabonne4 = $("<td>")
                            .text(abonne.telephoneAb); 
    
                thabonne1.appendTo(tr2);
                tdabonne1.appendTo(tr2);
                tdabonne2.appendTo(tr2);
                tdabonne3.appendTo(tr2);
                tdabonne4.appendTo(tr2);
                tr2.appendTo(tbody);
            });
        
            thead.appendTo(newAbonneTable);
            tbody.appendTo(newAbonneTable);
            newAbonneTable.appendTo(contentDisplay);
        } else {
            $('#abonnes-display-div').text("Pas d'abonné trouvé !");
        }
      })
      .fail(function(jqXHR, textStatus) {
        console.log("Issue with the Ajax call !");
        console.log(jqXHR);
        console.log(textStatus);
      });

}