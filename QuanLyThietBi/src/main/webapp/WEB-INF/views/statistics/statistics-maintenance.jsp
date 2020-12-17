 
 <%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Thống kê - báo cáo bảo trì thiết bị</h4>
				</div>
				<div class="clearfix"></div>
			</div>
				<div class="clearfix"></div>
			<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<c:url value="/statistics/maintenance/1" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
										 
											<div class="col-md-3 col-sm-3 ">
												<form:input path="dateTo" type="date" cssClass="form-control" placeholder="Từ ngày "/>
											</div>
											<div class="col-md-3 col-sm-3 ">
												<form:input path="dateFrom" type="date" cssClass="form-control" placeholder="Đến ngày"/>
											</div>
											<div class="col-md-3 col-sm-3 ">
												<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search"></i> Search</button>												
												<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>																
											</div>
										</div>				 
										<div class="ln_solid"></div>
									</form:form>
								</div>
							</div>
						</div>
					</div>

<button class="btn btn-info" onclick="exportExcel()"  ><i class="glyphicon glyphicon-export"></i> Excel</button>
<button class="btn btn-warning" onclick="exportPdf()"  ><i class="glyphicon glyphicon-export"></i> Pdf</button>
	<div class="table-responsive">
 
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">#</th>
                            <th class="column-title">Tên thiết bị</th>  
                            <th class="column-title">Tên người phụ trách</th>                        
                            <th class="column-title">Mô tả</th> 
                            <th class="column-title">Tình trạng</th>  
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${list}" var="item" varStatus="i"> 
                          	<tr>
	                            <td>${pageInfo.offSet + i.index + 1} </td>                       
	                        	<td>${item.productDTO.name}</td>
	                        	<td>${item.name}</td>
	                        	<td>${item.description}</td>  
	                        	<td>
		                        	<c:choose>
		                        		<c:when test="${item.status == 1}">
		                        			Đang xử lý
		                        		</c:when>
		                        		<c:otherwise>
		                        			Đã hoàn thành
		                        		</c:otherwise>
		                        	</c:choose>
	                        	</td>                
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
     <jsp:include page="/WEB-INF/views/layout/paging.jsp"/>                      


      </div>

	</div>
</div>

<script type="text/javascript">
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/category/list/'/>"+page);
		$("#searchForm").submit();
	}
	function exportExcel(){
		$("#searchForm").attr('action',"<c:url value='/statistics/maintenance/export-excel'/>");
		$("#searchForm").submit();
		$("#searchForm").attr('action',"<c:url value='/statistics/maintenance/1'/>");
	}
	function exportPdf(){
		$("#searchForm").attr('action',"<c:url value='/statistics/maintenance/export-pdf'/>");
		$("#searchForm").submit();
		$("#searchForm").attr('action',"<c:url value='/statistics/maintenance/1'/>");
	}
	$(document).ready(function(){
		var msgError = '${msgError}';
		var msgSuccess ='${msgSuccess}';
		if(msgError){
			new PNotify({
		        title: 'Thông Báo',
		        text: msgError,
		        type: 'error',
		        styling: 'bootstrap3'
		        
		    });	
		}
		if(msgSuccess){
			new PNotify({
		        title: 'Thông Báo',
		        text: msgSuccess,
		        type: 'success',
		        styling: 'bootstrap3'
		    });	
		}
	});
	
	$(document).ready(function(){
		$('#statistics-maintenance').parents("li").addClass('active').siblings().removeClass("active");
		$('#statistics-maintenance').addClass('current-page').siblings().removeClass("current-page");
		$('#statistics-maintenance').parents().show();
	});
	

	
</script>



 