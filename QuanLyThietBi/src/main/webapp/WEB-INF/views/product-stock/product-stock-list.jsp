<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Sản phẩm trong kho</h4>
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
									<c:url value="/product-stock/list/1" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Sản phẩm <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="productId" cssClass="form-control">
													<form:option value="0">Chọn sản phẩm</form:option>
													<c:forEach items="${listProduct }" var="item">
														<form:option value="${item.id}">${item.name}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Kho <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="stockId" cssClass="form-control">
													<form:option value="0">Chọn kho</form:option>
													<c:forEach items="${listInven }" var="item">
														<form:option value="${item.id}">${item.name}</form:option>
													</c:forEach>
												</form:select>
											</div>
										</div>
										<div class="ln_solid"></div>
										<div class="item form-group">
											<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-search"></i> Search</button>												
												<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>												
											</div>
										</div>

									</form:form>
								</div>
							</div>
						</div>
					</div>


	<div class="table-responsive">		
		<!-- 
			<a data-toggle="modal" data-target="#excel-modal"	href="javascript:void(0)" ><button class="btn btn-success" title="import sản phẩm"><i class="glyphicon glyphicon-import"></i> Import</button></a>
		<a href='<c:url value="/product-stock/excel-file"></c:url>'><button class="btn btn-default" title="lấy mẫu import"><i class="glyphicon glyphicon-file"></i> Document</button></a>
		 -->
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">#</th>
                            <th class="column-title">Id</th>
                            <th class="column-title">Tên sản phẩm</th>  
                            <th class="column-title">Giá </th>    
                            <th class="column-title">Số lượng </th>  
                            <th class="column-title">Kho</th>                   
                            <!-- <th class="column-title no-link last text-center" colspan="3" ><span class="nobr">Action</span> -->
                           
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${list}" var="item" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${item.id}</td>                        
                        	<td>${item.productDTO.name}</td>
                        	<td><fmt:formatNumber value="${item.price}"  pattern="###,#00"/></td>
                        	<td>${item.quantity}</td>
                        	<td>${item.inventoryDTO.name}</td>
                                      
                          	</tr>
                          </c:forEach>
							
                        </tbody>
                      </table>
     <jsp:include page="/WEB-INF/views/layout/paging.jsp"/>                      


      </div>

		<div id="excel-modal" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
				  <form action='<c:url value="/manage/product-stock/import-excel"></c:url>' method="post" enctype="multipart/form-data">
					<div class="modal-header">
						<p class="modal-title">Import sản phẩm</p>
						<button class="close" data-dismiss="modal" >&times;</button>
					</div>
					<div class="modal-body">
						<input type="file" name="file">
					</div>
						<div class="modal-footer">
							<button type="submit" class="btn btn-success" >Có</button>
							<button  class="btn btn-danger" data-dismiss="modal">Close</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	function gotoPage(page){
		$("#searchForm").attr('action',"<c:url value='/product-stock/list/'/>"+page);
		$("#searchForm").submit();
	}
	function deleteItem(id){
		if(confirm("Bạn có chắc chắn xóa nó không ?")){
			location.href="<c:url value='/product-stock/delete/'/>"+id;
		}
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
		$('#productstocklist').parents("li").addClass('active').siblings().removeClass("active");
		$('#productstocklist').addClass('current-page').siblings().removeClass("current-page");
		$('#productstocklist').parents().show();
	});
	

	
</script>



