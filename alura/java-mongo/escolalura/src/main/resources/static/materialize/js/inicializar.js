$(function() {

	$( document ).ready(function() {
		$('.datepicker').pickadate({
		    selectMonths: true,
		    selectYears: 20
		  });
		$('select').material_select();
	});
})