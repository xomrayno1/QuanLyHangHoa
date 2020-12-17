<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"%>
  <div class="right_col" role="main">
	   <div class="">
	   
 
	<div class="row">
            <div class="col-lg-8">
              <div class="card mb-3">
                <div class="card-header">
                  <i class="fa fa-chart-bar"></i>
                  Tổng giá trị nhập xuất hàng năm : 2020</div>
                <div class="card-body">
                  <canvas id="myChart" width="100%" height="50"></canvas>
                </div>
                <div class="card-footer small text-muted">Updated yesterday  </div>
              </div>
            </div>
           <div class="col-lg-4">
              <div class="card mb-3">
                <div class="card-header">
                  <i class="fa fa-chart-bar"></i>
                  Những sản phẩm nhập / xuất nhiều nhất </div>
                <div class="card-body">
                  <canvas id="myPieChartReceipt" width="100%" height="50"></canvas>
                </div>
                <div class="card-footer small text-muted">Updated yesterday  </div>
              </div>
            </div>  
   </div>
		    <div class="row">
		            <div class="col-lg-8">
		              <div class="card mb-3">
		                <div class="card-header">
		                  <i class="fa fa-chart-bar"></i>
		                  Số đơn xuất nhập hàng năm : 2020</div>
		                <div class="card-body">
		                  <canvas id="myChartGoods" width="100%" height="50"></canvas>
		                </div>
		                <div class="card-footer small text-muted">Updated yesterday  </div>
		              </div>
		            </div>
		            <div class="col-lg-4">
		              <div class="card mb-3">
		                <div class="card-header">
		                  <i class="fa fa-chart-bar"></i>
		                   </div>
		                <div class="card-body">
		                  <canvas id="myPieChartIssued" width="100%" height="50"></canvas>
		                </div>
		                <div class="card-footer small text-muted">Updated yesterday  </div>
		              </div>
           	 </div> 
		   	</div>
	   </div>
   </div>  
 <script type="text/javascript">
 	var objectReceipt = ${barcharJsonReceipt};
 	var objectIssued = ${barcharJsonIssued};
 	var objectCountReceipt = ${barcharCountJsonReceipt};
 	var objectCountIssued = ${barcharCountJsonIssued};
 	var chartDataReceipt = ${pieChartJsonReceipt};
 	var chartDataIssued = ${pieChartJsonIssued};
 	 
 </script>
 <script type="text/javascript" src='<c:url value="/resources/js/chart/goodschart.js"></c:url>'></script>
 <script type="text/javascript" src='<c:url value="/resources/js/chart/goodschart2.js"></c:url>'></script>
 <script type="text/javascript" src='<c:url value="/resources/js/chart/pieChartReceipt.js"></c:url>'></script>
  <script type="text/javascript" src='<c:url value="/resources/js/chart/pieChartIssued.js"></c:url>'></script>
</body>
</html>