 
	var countGoodsReceipt = [];
 	var countGoodsIssued = [];
 	
 	var receiptLength = objectCountReceipt.length;
 	var issuedLength = objectCountIssued.length;
 	
 	for(var i = 0 ; i < receiptLength ; i++){
 		countGoodsReceipt[i] = objectCountReceipt[i].value;
 		countGoodsIssued[i] = objectCountIssued[i].value
 	}
	$(document).ready(function(){
 		getChartDataCount();
 	})
	function getChartDataCount(){
		 var ctx = document.getElementById('myChartGoods').getContext('2d');
		 var myChart = new Chart(ctx, {
		     type: 'bar',
		     data: {
		         labels: ["January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
		         datasets: [{
			        	 label: 'So Don Nhap ', 
			             data: countGoodsReceipt,
			             backgroundColor: [
			                 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)',  
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',   
			            	 'rgba(0, 28, 255, 0.7)',
			            	 'rgba(0, 28, 255, 0.7)' 
			             ],
			             borderColor: [
			                 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)',
			            	 'rgba(255, 99, 132, 1)'
			             ],
			             borderWidth: 1
			         },
		         	{
			        	 label: 'So Don Xuat', 
			             data: countGoodsIssued,
			             backgroundColor: [
			            	 'rgba(255, 208, 138, 1)',
			            	  'rgba(255, 208, 138, 1)',
			            	 'rgba(255, 208, 138, 1)',
			            	 'rgba(255, 208, 138, 1)',
			            	 'rgba(255, 208, 138, 1)',
			            	 'rgba(255, 208, 138, 1)',
			            	 'rgba(255, 208, 138, 1)',
			            	  'rgba(255, 208, 138, 1)',
			            	 'rgba(255, 208, 138, 1)',
			            	 'rgba(255, 208, 138, 1)',
			            	  'rgba(255, 208, 138, 1)',
			            	  'rgba(255, 208, 138, 1)'
			             ],
			             borderColor: [
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)',
			                 'rgba(255, 99, 132, 1)'
			             ],
			             borderWidth: 1
			        } 
		         ] 
		     },
		     options: {
		         scales: {
		             yAxes: [{
		                 ticks: {
		                     beginAtZero: true
		                 }
		             	 
		             }]
		         }
		     }
		 });
	}
 	
 