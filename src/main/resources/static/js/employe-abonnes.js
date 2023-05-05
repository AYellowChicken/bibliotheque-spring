var lastSearch;

function appendAbonnesNumAbonne() {

    lastSearch = appendAbonnesNumAbonne;

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
            const th2 = $("<th>").text("Prénom")
                        .attr("scope", "col");
            const th3 = $("<th>").text("Nom")
                        .attr("scope", "col");
            const th4 = $("<th>").text("Adresse")
                        .attr("scope", "col");
            const th5 = $("<th>").text("Téléphone")
                        .attr("scope", "col");
            const th6 = $("<th>").text("Update")
                        .attr("scope", "col");             
            const th7 = $("<th>").text("Delete")
                        .attr("scope", "col");

            th1.appendTo(tr);
            th2.appendTo(tr);
            th3.appendTo(tr);
            th4.appendTo(tr);
            th5.appendTo(tr);
            th6.appendTo(tr);
            th7.appendTo(tr);
            tr.appendTo(thead);

            const tbody = $("<tbody>");
            const tr2 = $("<tr>");
            var thabonne1 = $("<th>")
                        .text(abonne.numAbonne)
                        .attr("scope", "row");
            var tdabonne1 = $("<td>")
                        .text(abonne.prenomAb);
            var tdabonne2 = $("<td>")
                        .text(abonne.nomAb);                 
            var tdabonne3 = $("<td>")
                        .text(abonne.adresseAb);             
            var tdabonne4 = $("<td>")
                        .text(abonne.telephoneAb);
            
            var tdabonne5 = $("<td>");
            var buttonUpdate = $("<button>")
                            .addClass("btn btn-primary")
                            .text("Update Abonné")
                            .on("click", function() {
                                appendUpdateAbonneForm(abonne);
                            });            
            tdabonne5.append(buttonUpdate);
            
            var tdabonne6 = $("<td>");
            var buttonDelete = $("<button>")
                            .addClass("btn btn-primary")
                            .text("Delete Abonné")
                            .on("click", function() {
                                deleted = deleteAbonne(abonne.numAbonne);
                                if (deleted) {
                                    tr2.remove();
                                    successfulDelete(abonne.numAbonne);
                                }
                            }); 
            tdabonne6.append(buttonDelete);
                        
            
            thabonne1.appendTo(tr2);
            tdabonne1.appendTo(tr2);
            tdabonne2.appendTo(tr2);
            tdabonne3.appendTo(tr2);
            tdabonne4.appendTo(tr2);
            tdabonne5.appendTo(tr2);
            tdabonne6.appendTo(tr2);
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

    lastSearch = appendAbonnesNomPrenom;

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
            const th2 = $("<th>").text("Prénom")
                        .attr("scope", "col");
            const th3 = $("<th>").text("Nom")
                        .attr("scope", "col");
            const th4 = $("<th>").text("Adresse")
                        .attr("scope", "col");
            const th5 = $("<th>").text("Téléphone")
                        .attr("scope", "col");
            const th6 = $("<th>").text("Update")
                        .attr("scope", "col");             
            const th7 = $("<th>").text("Delete")
                        .attr("scope", "col");
           
            th1.appendTo(tr);
            th2.appendTo(tr);
            th3.appendTo(tr);
            th4.appendTo(tr);
            th5.appendTo(tr);
            th6.appendTo(tr);
            th7.appendTo(tr);
            tr.appendTo(thead);
                
            const tbody = $("<tbody>");

            data.forEach(function(abonne) {
                var tr2 = $("<tr>");
                var thabonne1 = $("<th>")
                            .text(abonne.numAbonne)
                            .attr("scope", "row");
                var tdabonne1 = $("<td>")
                            .text(abonne.prenomAb);
                var tdabonne2 = $("<td>")
                            .text(abonne.nomAb);                 
                var tdabonne3 = $("<td>")
                            .text(abonne.adresseAb);             
                var tdabonne4 = $("<td>")
                            .text(abonne.telephoneAb); 

                var tdabonne5 = $("<td>");
                var buttonUpdate = $("<button>")
                                .addClass("btn btn-primary")
                                .text("Update Abonné")
                                .on("click", function() {
                                    appendUpdateAbonneForm(abonne);
                                });            
                tdabonne5.append(buttonUpdate);
                
                var tdabonne6 = $("<td>");
                var buttonDelete = $("<button>")
                                .addClass("btn btn-primary")
                                .text("Delete Abonné")
                                .on("click", function() {
                                    deleted = deleteAbonne(abonne.numAbonne);
                                    if (deleted) {
                                        tr2.remove();
                                        successfulDelete(abonne.numAbonne);
                                    }
                                });
                tdabonne6.append(buttonDelete);

                thabonne1.appendTo(tr2);
                tdabonne1.appendTo(tr2);
                tdabonne2.appendTo(tr2);
                tdabonne3.appendTo(tr2);
                tdabonne4.appendTo(tr2);
                tdabonne5.appendTo(tr2);
                tdabonne6.appendTo(tr2);
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

function appendUpdateAbonneForm(abonne) {
    const divUpdate = $("#abonnes-update-div");
    divUpdate.empty();

    const updateForm = $("<form>")
                .attr("id", "updateAbonnesForm")
                .attr("onSubmit", "event.preventDefault(); updateAbonne()");

    const input1 = $("<input>")
                .attr("type", "number")
                .attr("id", "numAbonneToUpdate")
                .val(abonne.numAbonne);


    const input2 = $("<input>")
                .attr("type", "text")
                .attr("id", "prenomAbToUpdate")  
                .val(abonne.prenomAb);
                
    const input3 = $("<input>")
                .attr("type", "text")
                .attr("id", "nomAbToUpdate")
                .val(abonne.nomAb);
    
    const input4 = $("<input>")
                .attr("type", "text")
                .attr("id", "addresseAbToUpdate")
                .val(abonne.addresseAb);

    const input5 = $("<input>")
                .attr("type", "text")
                .attr("id", "telephoneAbToUpdate")
                .val(abonne.telephoneAb);

    const submitButton = $("<button>")
                    .attr("type", "submit")
                    .text("Mettre à jour l'abonné");

    input1.appendTo(updateForm);
    input2.appendTo(updateForm);
    input3.appendTo(updateForm);
    input4.appendTo(updateForm);
    input5.appendTo(updateForm);
    submitButton.appendTo(updateForm);

    updateForm.appendTo(divUpdate);

}

function createAbonne() {
    const prenomAbCreate = $("#prenomAbCreate").val();
    const nomAbCreate = $("#nomAbCreate").val();
    const addressAbCreate = $("#addressAbCreate").val();
    const telephoneAbCreate = $("#telephoneAbCreate").val();
    const urlLink = "http://localhost:8080/abonnes";
    
    $.ajax({
        method: "POST", // HTTP method
        url: urlLink,
        contentType: 'application/json',
        data: JSON.stringify({
            prenomAb: prenomAbCreate, 
            nomAb: nomAbCreate,
            addressAb: addressAbCreate,
            telephoneAb: telephoneAbCreate
        }),
        dataType: "json" // Expected data type from the server (optional)
      })
      .done(function(abonne) {
        console.log(abonne);
        successfulCreate(abonne.numAbonne);
      })
      .fail(function(jqXHR, textStatus) {
        console.log("Failed create");
        console.log(jqXHR);
        console.log(textStatus);
        failedCreate();
    })
}


function updateAbonne() { 
    
    const abonneNumAbonneUpdate = $("#numAbonneToUpdate").val()
    const abonnePrenomUpdate = $("#prenomAbToUpdate").val();
    const abonneNomUpdate = $("#nomAbToUpdate").val();
    const abonneAddresseUpdate = $("#adresseAbToUpdate").val();
    const abonneTelephoneUpdate = $("#telephoneAbToUpdate").val();
    const urlLink = "http://localhost:8080/abonnes";
    
    $.ajax({
        method: "PUT", // HTTP method
        url: urlLink,
        contentType: 'application/json',
        data: JSON.stringify({
            numAbonne: abonneNumAbonneUpdate,
            prenomAb: abonnePrenomUpdate, 
            nomAb: abonneNomUpdate,
            addressAb: abonneAddresseUpdate,
            telephoneAb: abonneTelephoneUpdate
        }),
        dataType: "json" // Expected data type from the server (optional)

      })
      .done(function(data) {
        console.log("Updated successfully");
        console.log(data);
        successfulUpdate(abonneNumAbonneUpdate);
        $("#abonnes-update-div").empty();
        return true;
      })
      .fail(function(jqXHR, textStatus) {
        console.log("Failed update");
        console.log(jqXHR);
        console.log(textStatus);
        failedUpdate(abonneNumAbonneUpdate);
        return false;
    })
    
}

function deleteAbonne(numAbonne) { 
    const urlLink = "http://localhost:8080/abonnes/" + numAbonne;
    
    console.log("Deleting abonné with URL " + urlLink);

    $.ajax({
        method: "DELETE", // HTTP method
        url: urlLink
      })
      .done(function(data) {
        console.log("Deleted successfully");
        console.log(data);
        return true;
    })
    .fail(function(jqXHR, textStatus) {
        console.log("Failed delete");
        console.log(jqXHR);
        console.log(textStatus);
        failedUpdate(numAbonne);
        return false;
    })
}


// TODO : This is UGLY. I'm sorry I ever wrote something like this but I need more coffee
function successfulDelete(numAbonne) {
    const alert = $("<div>")
                .addClass("alert alert-success")
                .attr("role", "alert")
                .text("Successfully deleted abonné n° " + numAbonne);
    alert.appendTo($("body"));
    setTimeout(function() {
        alert.remove();
    }, 5000);
}

function successfulUpdate(numAbonne) {
    const alert = $("<div>")
                .addClass("alert alert-success")
                .attr("role", "alert")
                .text("Successfully updated abonné n° " + numAbonne);
    alert.appendTo($("body"));
    setTimeout(function() {
        alert.remove();
    }, 5000);

    lastSearch();
}


function successfulCreate(numAbonne) {
    const alert = $("<div>")
                .addClass("alert alert-success")
                .attr("role", "alert")
                .text("Successfully created abonné n° " + numAbonne);
    alert.appendTo($("body"));
    setTimeout(function() {
        alert.remove();
    }, 5000);

    lastSearch();
}

function failedDelete(numAbonne) {
    const alert = $("<div>")
                .addClass("alert alert-error")
                .attr("role", "alert")
                .text("Failed to delete abonné n° " + numAbonne);
    alert.appendTo($("body"));
    setTimeout(function() {
        alert.remove();
    }, 5000);
}
function failedCreate() {
    const alert = $("<div>")
                .addClass("alert alert-error")
                .attr("role", "alert")
                .text("Failed to create abonné");
    alert.appendTo($("body"));
    setTimeout(function() {
        alert.remove();
    }, 5000);
}

function failedUpdate(numAbonne) {
    const alert = $("<div>")
                .addClass("alert alert-danger")
                .attr("role", "alert")
                .text("Failed to update abonné n° " + numAbonne);
    alert.appendTo($("body"));
    setTimeout(function() {
        alert.remove();
    }, 5000);

}