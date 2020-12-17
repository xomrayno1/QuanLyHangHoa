 
console.log(chartDataReceipt)
var arrayLength = chartDataReceipt.length;
var numericData = [];
var labelData = [];

for (var i = 0 ; i < arrayLength ; i++){
	numericData[i] = chartDataReceipt[i].value;
	labelData[i]  = chartDataReceipt[i].label;
}

 new Chart(document.getElementById("myPieChartReceipt"), {
    type: 'pie',
     data: {
        labels: labelData,
        datasets: [{
            label: 'My First dataset',
            backgroundColor: ['#d35400', '#00a63f', '#900C3F', '#2e4053', '#1a5276','#f4d03f','#8e44ad'
            		,'#DAF7A6','#34eb13','#eb1d13' ],
            borderColor: 'rgb(255, 99, 132)',
            data: numericData
        }]
    },

    // Configuration options go here
    options: {
    	title : {
    		display : true,
    		text : " Nhung san pham nhap nhieu nhat"
    	}
    }
});

 