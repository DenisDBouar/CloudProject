var rootURL = "https://"+window.location.hostname+"/rest/";
//var rootURL = "http://localhost:8080/server/rest/";
$(document).ready(function(){
	//loadpage();

	  $("#add").click(function(){
		  adddata();
	  });
	  
	  $("#delete").click(function(){
		  deletedataa();
	  });
	  
	  $("#update").click(function(){
		  updatedata();
	  });
	  
	  $("#search").click(function(){
		  searchpage2();
	  });
	  
	  //instagram
	  $("#header-search-submit").click(function(){
		  searchinstagram();
	  });
	
});

function adddata(){
	$.ajax({
		type: "POST",
		url: rootURL + "addproduct", 
		data: "{\"ID\": \""+ $('#id').val() +"\", \"name\": \""+ $('#nameText').val() +"\", \"product\": \""+ $('#productText').val() +"\", \"emp_no\": \""+ $('#numberText').val() +"\"}",
		contentType:"text/html",
		
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Add data to chart Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
		},
		success: function(data, textStatus, jqXHR){
			loadpage();
		},
		complete: function(XMLHttpRequest) {
		}, 
	});
}

function deletedataa(){
	 $.ajax({
			type: 'DELETE',
			url: rootURL + "deleteproduct" + '/' + "{ \"ID\" : \""+ $('#id').val() +"\" }",
			success: function(data, textStatus, jqXHR){
				loadpage();
			},
			error: function(jqXHR, textStatus, errorThrown){
				alert('deleteWine error');
			}
		});
}

function updatedata(){
	$.ajax({
		type: "PUT",
		url: rootURL + "updateproduct/ID/"+ $('#id').val(), 
		data: "name,"+ $('#nameText').val() +",product,"+ $('#productText').val() +",emp_no,"+ $('#numberText').val(), 
		contentType:"text/html",
		
		error: function(jqXHR, textStatus, errorThrown) {
			alert("Add data to chart Error " + jqXHR.getAllResponseHeaders() + " " + errorThrown);
		},
		success: function(data, textStatus, jqXHR){
			loadpage();
		},
		complete: function(XMLHttpRequest) {
		}, 
	});
}

function searchpage(){
	 $.ajax({
		  type: 'GET',
		  url: rootURL + "serchproduct/"+ $('#numberText').val(),
		  success:function(data, textStatus, jqXHR){
		    var res = "";
		    var servers = JSON.parse(data);
		    var res = '<table id="t01"><tr>'+
			        			'<th>Name</th>'+
			        			'<th>Product</th>'+	
			        			'<th>Number</th>'+
		        			'</tr>';
		    for(var i = 0; i<servers.Cart.length; i++){
		    	res = res + '<tr>';
		    	res = res + '<td onclick="moveValue('+ servers.Cart[i].ID +')">'+ servers.Cart[i].ID +'</td>';
		    	res = res + '<td id="name_'+ servers.Cart[i].ID +'"><a href="http://www.ebay.com/sch/'+ servers.Cart[i].name +'">'+ servers.Cart[i].name +'</a></td>';
		    	res = res + '<td id="prod_'+ servers.Cart[i].ID +'">'+ servers.Cart[i].product +'</td>';
		    	res = res + '<td id="number_'+ servers.Cart[i].ID +'"style="width: 100px;">$'+ servers.Cart[i].emp_no +'</td>';
		    	res = res + '</tr">';
		    }
		    res = res +'</tbody>';
		    $("#page2").html(res);
		  },
	  	 error: function(jqXHR, textStatus, errorThrown){
			alert('About page error: ' + textStatus);
		}  
	});
}

function searchpage2(){
	 $.ajax({
		  type: 'GET',
		  url: rootURL + "serchproduct2/"+ $('#searchText').val(),
		  success:function(data, textStatus, jqXHR){
		    var res = "";
		    var servers = JSON.parse(data);
		    var res = '<table id="t01"><tr>'+
			        			'<th>Name</th>'+
			        			'<th>Product</th>'+	
			        			'<th>Number</th>'+
			        			'<th>Price</th>'+
		        			'</tr>';
		    for(var i = 0; i<servers.Cart.length; i++){
		    	res = res + '<tr>';
		    	res = res + '<td onclick="moveValue('+ servers.Cart[i].ID +')">'+ servers.Cart[i].ID +'</td>';
		    	res = res + '<td id="name_'+ servers.Cart[i].ID +'"><a href="http://www.ebay.com/sch/'+ servers.Cart[i].name +'">'+ servers.Cart[i].name +'</a></td>';
		    	res = res + '<td id="prod_'+ servers.Cart[i].ID +'">'+ servers.Cart[i].product +'</td>';
		    	res = res + '<td id="number_'+ servers.Cart[i].ID +'"style="width: 100px;">$'+ servers.Cart[i].emp_no +'</td>';
		    	res = res + '</tr">';
		    }
		    res = res +'</tbody>';
		    $("#page2").html(res);
		  },
	  	 error: function(jqXHR, textStatus, errorThrown){
			alert('About page error: ' + textStatus);
		}  
	});
}

function loadpage(){
	  $.ajax({
		  type: 'GET',
		  url: rootURL + "gettable",
		  success:function(data, textStatus, jqXHR){
		    var res = "";
		    var servers = JSON.parse(data);
		    var res = '<table id="t01"><tr>'+
			        			'<th>ID</th>'+
			        			'<th>Name</th>'+
			        			'<th>Description</th>'+	
			        			'<th>Price</th>'+
		        			'</tr>';
		    for(var i = 0; i<servers.Cart.length; i++){
		    	res = res + '<tr>';
		    	res = res + '<td onclick="moveValue('+ servers.Cart[i].ID +')">'+ servers.Cart[i].ID +'</td>';
		    	res = res + '<td id="name_'+ servers.Cart[i].ID +'"><a href="http://www.ebay.com/sch/'+ servers.Cart[i].name +'">'+ servers.Cart[i].name +'</a></td>';
		    	res = res + '<td id="prod_'+ servers.Cart[i].ID +'">'+ servers.Cart[i].product +'</td>';
		    	res = res + '<td id="number_'+ servers.Cart[i].ID +'"style="width: 100px;">$'+ servers.Cart[i].emp_no +'</td>';
		    	res = res + '</tr">';
		    }
		    res = res +'</tbody>';
		    $("#page2").html(res);
		  },
	  	 error: function(jqXHR, textStatus, errorThrown){
			alert('About page error: ' + textStatus);
		}  
	});
}

function loadpageUtro(){
	  $.ajax({
		  type: 'GET',
		  url: rootURL + "utroru",
		  success:function(data, textStatus, jqXHR){
			  var myWindow = window.open("", "_self");
			    myWindow.document.write("<p>I replaced the current window.</p>");
		  },
	  	 error: function(jqXHR, textStatus, errorThrown){
			alert('About page error: ' + textStatus);
		}  
	});
}

function moveValue(y)
{
	$('#id').val(y);
	$('#nameText').val($('#name_'+y).text());
	$('#productText').val($('#prod_'+y).text());
	$('#numberText').val($('#number_'+y).text());
} 


//instagram
	 function searchinstagram(){
		 $.ajax({
			  type: 'GET',
			  url: rootURL + "getinstagramdata/"+ $('#header-keyword').val() + "/"+ $('#header-distance').val(),
			  success:function(data, textStatus, jqXHR){
			
				  var beaches = [];
				  var res = "";
				  var servers = JSON.parse(data);
				  var res = servers.citylocation.lat+'<table id="t01"><tr>'+
					        			'<th>ID</th>'+
					        			'<th>Name</th>'+
				        			'</tr>';
				    for(var i = 0; i<servers.data.length; i++){
				    	beaches.push([servers.data[i].location.name, 
				    	              servers.data[i].location.latitude, 
				    	              servers.data[i].location.longitude, 
				    	              i+1, 
				    	              servers.data[i].images.standard_resolution.url,
				    	              servers.data[i].user.username,
				    	              servers.data[i].link
				    	             ]);
				    }
			    google.maps.event.addDomListener(window, 'load', initialize(servers.citylocation.lat, servers.citylocation.lng, beaches));
			  },
		  	 error: function(jqXHR, textStatus, errorThrown){
				alert('About page error: ' + textStatus);
			}  
		});
}


function initialize(lat, lng, beaches) {
	  var mapOptions = {
	    zoom: +15,
	    center: new google.maps.LatLng(lat, lng)
	  }
	  var map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	  setMarkers(map, beaches);
	}
function setMarkers(map, locations) {
  for (var i = 0; i < locations.length; i++) {
    var beach = locations[i];
    var myLatLng = new google.maps.LatLng(beach[1], beach[2]);
    var marker = new google.maps.Marker({
        position: myLatLng,
        map: map,	
        title: beach[0],
        zIndex: beach[3],
        imageURL: beach[4],
    	userName: beach[5],
    	linkName: beach[6]
    });
    attachSecretMessage(marker, "aa"+i);

  }
}

function attachSecretMessage(marker, num) {
	  google.maps.event.addListener(marker, 'click', function() {
	  //alert(marker.get('position'));
	  $("#info").html('Location name: '+ marker.get('title') + '<br>' +
					  'User name: '+ marker.get('userName') + '<br>' +
					  'User link: <a href="'+ marker.get('linkName') +'">'+ marker.get('linkName') +'</a><br>'
					  );
	  $("#img").html('<img src="'+ marker.get('imageURL') +'" style="width:640px;height:640px;">');

	  });
	}

	
