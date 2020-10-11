
$('body').on('click', '.ajax_request', function(e) {
	
	e.preventDefault();
	
	var url = $(this).closest("form").attr("id");
	var value="";
	
	$("#"+url + " input").each(function(){
		
		value += $(this).val()
	})
	ajaxRequest(url,value);
})

$("body").on("click",".ajax_delete_request",function(e){
	e.preventDefault();
	
	var url = $(this).closest("form").attr("id");
	var inputData = $("#"+url).serialize();
	
	$.ajax({
		url:url,
		type:"get",
		data:inputData,
		dataType:"text",
		success:function(data){
			$("#server_message").text(data);
		},
		error:function(e){
			console.log(e);
		}
	})
})

function ajaxRequest(url,input_value){
	
	$.ajax({
		
	url:url,
	type :"get",
	data:"value="+input_value,
	//dataType : 'json',
	
	success:function(data){
		$(".display").empty();
		$("#server_message").empty();
		
		if(typeof data == "string"){
			$("#server_message").addClass("alert alert-info")
			                    .text(data)
			                    .fadeIn(2000).delay(3000).fadeOut(2000);
		
		}else{
			
		var list = [];
		var key = "";
		
		for(var value in data){
			if(data[value] == null || data[value].length == 0){
			
			$("#server_message").addClass("alert alert-info")
			                    .text("Nisu pronadjena poklapanja za vas unos")
			                    .fadeIn(2000).delay(3000).fadeOut(2000);
			return false;
			}
			
			list.push(data[value]);
			key = value;
			
		}
		
		switch(key){
		
		case "book" :
			showBookTable(list);
			break;
			
		case "user" :
			showUserTable(list);
			break;
		
		case "reserve_book" :
			showAvailableBookTable(list);
			break;
			
		case "user_reservation" :
			$("#server_message").text("Rezervacije izabranog korisnika :");
			showBookTable(list);
			break;
			
		case "title_reservation" :
			$("#server_message").text("Korisnici izabrane knjige :");
			showUserTable(list);
			break;
			
		case "author_reservation" :
			$("#server_message").text("Rezervacije izabranog pisca :");
			showBookTable(list);
			break;
			
		}
		
		}
		
		
	},	
	
	error : function(e){
		console.log("error")
	}
	})
}		
		
  function showBookTable(data){
		
  var arrayOfRows = [];
	
	var table = "<table class='table'>" +
			    "<thead>" +
			    "<tr>" +
	            "<th>Naziv Knjige</th>" +
	            "<th>Ime Pisca</th>" +
	            "<th>Prezime Pisca</th>"+
	            "<th>Broj Primjeraka</th>" +
	            "<th>Broj Dostupnih Primjeraka</th>" +
	            "</tr>" +
	            "</thead>" +
	            "</table>";
	
	$(".display").append(table);
	
	$(data[0]).each(function(i){
		 
	var currentRows = "<tr>" +
        		      "<td>" + this.title + "</td>" +
        		      "<td>" + this.author.firstName + "</td>" +
        	          "<td>" + this.author.lastName + "</td>" +
		              "<td>" + this.totalInstances + "</td>" +
		              "<td>" + this.availableInstances + "</td>" +
		              "</tr>";	
		 
        arrayOfRows[i]= currentRows;
	 
	})//end each
	
	printTable(arrayOfRows);
          
	}   
	
	
	function showUserTable(data){
		
		var arrayOfRows = [];
		
		var table = "<table class='table'>" +
			        "<thead>" +
			        "<tr>" +
			        "<th>ID</td>" +
			        "<th>Ime</th>" +
			        "<th>Prezime</th>" +
			        "<th>Korisnicko Ime</th>" +
			        "<th>E-mail</th>" +
			        "</tr>" + 
			        "</thead>" + 
			        "</table>";
		
		$(".display").append(table);
		
		$(data[0]).each(function(i){
			
			var currentRows = "<tr>" +
			                  "<td>" + this.id + "</td>" + 
			                  "<td>" + this.firstName + "</td>" +
			                  "<td>" + this.lastName + "</td>" +
			                  "<td>" + this.username + "</td>" +
			                  "<td>" + this.email + "</td>" + 
			                  "</tr>";
			
			arrayOfRows[i] = currentRows;
		})
		
		printTable(arrayOfRows);
		
	}
	
	function showAvailableBookTable(data){
		
		var arrayOfRows = [];
		
		var table = 
		"<form action=#>" +
		"<table class='table'>" +
	    "<thead'>" +
	    "<tr>" +
        "<th>Naziv Knjige</th>" +
        "<th>Ime Pisca</th>" +
        "<th>Prezime Pisca</th>"+
        "<th>Rezervacija</th>" + 
        "</tr>" +
        "</thead>" +
        "</table>"+
        "<br/><br/>" +
        "<button onclick=prepareRequest() type=button class='btn btn-primary'>Rezervisi Knjigu</button>"
        "</form>";

        $(".display").append(table);
        
        $(data[0]).each(function(i){
   		
  var currentRows = '<tr>' +
        			'<td class=active>' + this.title + '</td>' +
        			'<td>' + this.author.firstName + '</td>' +
        			'<td>' + this.author.lastName + '</td>' +
        			'<td><input type=radio name=book_reservation value="' + this.title + '"/></td>' +
        			"</tr>";	
        		 
                arrayOfRows[i]= currentRows;
        	 
        	})//end each
        	
        	printTable(arrayOfRows);
	
	}
	
	function showUsersReservation(data){
		
	}
	
	function printTable(rows){
		var x = 0;
		
		var interval = setInterval(function(){
			printRow()
		},50)
		
		function printRow(){
			
			if(x == rows.length){
				clearInterval(interval);
			}
			
			$(".display table").append(rows[x]);
			x++;
		}
	}
	

function prepareRequest(){
	var value = "";
	var boo = false;
	
	$("form input:radio").each(function(){
		if($(this).is(":checked")){
			value = $(this).val();
			boo = true;
		}
	})
	
	if(boo){
		
		var url = "reserve_book";
		ajaxRequest(url,value);
	}else{
		$("#server_message").addClass("alert alert-info")
                            .text("Knjiga nije odabrana")
                            .fadeIn(2000).delay(3000).fadeOut(2000);
	}
	
	
}


	
