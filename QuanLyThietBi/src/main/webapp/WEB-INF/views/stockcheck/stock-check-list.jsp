<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Kiểm Kê</h4>
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
									<c:url value="/stock-check/list/1" var="searchUrl"></c:url>
									<form:form servletRelativeAction="${searchUrl}" method="POST" modelAttribute="searchForm" cssClass="form-horizontal form-label-left">
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Mã sản phẩm <span class="required">  </span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												 <form:input path="id"      planceholder="Nhập mã và tìm kiếm" cssClass="form-control"/>
											</div>
										</div>
										 
										 
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for=status>Tình trạng  <span class="required"> </span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="status" cssClass="form-control">
													<form:option value="0">Tất cả</form:option>
													<form:option value="1">Đang kiểm kê</form:option>
													<form:option value="2">Đã kiểm kê</form:option>
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
		<a href='<c:url value="/stock-check/add"></c:url>'><button class="btn btn-primary"><i class="glyphicon glyphicon-plus"></i>Thêm</button></a>
		 
		 
		 
                      <table class="table table-striped jambo_table bulk_action">
                        <thead>
                          <tr class="headings">
                            <th class="column-title">#</th>
                            <th class="column-title">Id</th>
                            <th class="column-title">Thời gian</th>
                            <th class="column-title">Tên sản phẩm</th>  
                            <th class="column-title">Kho</th>
                            <th class="column-title">Tồn kho </th>
                            <th class="column-title">Tồn thực tế </th>  
                            <th class="column-title">Chênh lệch</th>
                            <th class="column-title">Người kiểm</th>  
                            <th class="column-title">Trạng thái</th>          
                            <th class="column-title no-link last text-center" colspan="3" ><span class="nobr">Action</span>
                            </th>
                          </tr>
                        </thead>

                        <tbody>
                          <c:forEach items="${list}" var="item" varStatus="i"> 
                          	<tr>
                            <td>${pageInfo.offSet + i.index + 1} </td>
                            <td>${item.id}</td>  
                            <td>
                           	 	<fmt:formatDate value="${item.createDate}" pattern="dd/MM/yyyy"/>
                            </td>                      
                        	 <td>${item.productName}</td> 
                        	 <td>${item.invenName}</td>  
                        	 <td>${item.quantity}</td>  
                        	 <td>${item.actualQuantity}</td>  
                        	 <td>${item.difference}</td>  
                        	 <td>${item.userName}</td>   
                        	 <td>${item.getStatusUTF8()}</td> 
                            <td colspan="3" class="last text-center">          
	                            <a href='<c:url value="/stock-check/view/${item.id}"></c:url>' class="btn btn-primary"><i class="glyphicon glyphicon-eye-open"></i></a>
	                            <a href='<c:url value="/stock-check/edit/${item.id}"></c:url>' class="btn btn-warning"><i class="glyphicon glyphicon-edit"></i></a>  
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
		$("#searchForm").attr('action',"<c:url value='/stock-check/list/'/>"+page);
		$("#searchForm").submit();
	}
	function deleteItem(id){
		if(confirm("Bạn có chắc chắn xóa nó không ?")){
			location.href="<c:url value='/stock-check/delete/'/>"+id;
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
		$('#stockchecklist').parents("li").addClass('active').siblings().removeClass("active");
		$('#stockchecklist').addClass('current-page').siblings().removeClass("current-page");
		$('#stockchecklist').parents().show();
	});
	

	
</script>



