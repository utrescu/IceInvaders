/**
 * Previsualitza la icona de l'usuari.
 */

$(document).ready(function() {
	// Canvia la icona quan es carrega una imatge nova
	$("#icona").change(function() {
		var oFReader = new FileReader();
		oFReader.readAsDataURL(this.files[0]);		
		oFReader.onload = function(carregaEvent) {
			$('#profile').attr("src", carregaEvent.target.result);
		};
	});
	
	// Recorda la darrera pestanya
	$(function() { 
	    // for bootstrap 3 use 'shown.bs.tab', for bootstrap 2 use 'shown' in the next line
	    $('a[data-toggle="tab"]').on('shown.bs.tab', function () {
	        // save the latest tab; use cookies if you like 'em better:
	        localStorage.setItem('lastTab', $(this).attr('href'));
	    });

	    // go to the latest tab, if it exists:
	    var lastTab = localStorage.getItem('lastTab');
	    if (lastTab) {
	        $('[href="' + lastTab + '"]').tab('show');
	    }
	});	
});