<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>

<div class="right_col" role="main">
	<div class="">
			<div class="page-title">
				<div class="title_left">
					<h4>Lịch sử bảo trì</h4>
					<p><strong>Tên sản phẩm</strong> : ${maintenance.productDTO.name} - ${maintenance.productDTO.code}</p>
					<p><strong>Người phụ trách</strong> : ${maintenance.name}</p>
				</div>
				<div class="clearfix"></div>
			</div>
			<div class="row">									
						<div class="col-md-12 col-sm-12 ">
							<div id="alertshow">
								
							</div>
							<div class="x_panel">
								<div class="x_content">
									<br />
																		
									<form:form   servletRelativeAction="/history/save/${maintenance.id}"  enctype="multipart/form-data" modelAttribute="submitForm" method="POST" cssClass="form-horizontal form-label-left">
													<div class="item form-group">
														<label class="col-form-label col-md-3 col-sm-3 label-align" for="multipartFile">Hình ảnh <span class="required">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">												
															<form:input path="multipartFile" type="file" cssClass="form-control" readonly="${viewOnly}"/>
															<form:hidden path="maintenanceId" value="${maintenance.id}"/>
															<div class="has-error">
																<form:errors path="multipartFile" cssClass="help-block"/>
															</div>
														</div>
													</div>
													<div class="item form-group">
														<label class="col-form-label col-md-3 col-sm-3 label-align" for="description">Mô tả<span class="required">*</span>
														</label>
														<div class="col-md-6 col-sm-6 ">
															<form:textarea path="description" cssClass="form-control" readonly="${viewOnly}"/>
														</div>
													</div>
													<div class="ln_solid"></div>
														<div class="item form-group">
															<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
															<c:if test="${!viewOnly}">
																<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
																<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
															</c:if>			
															<a href='<c:url value="/maintenance/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
															</div>
														</div>
												</form:form>
								</div>
							</div>
						</div>
					</div>
				<div class="clearfix"></div>
			<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">							
							<div class="x_panel" style="">
							    <div class="x_content">							  
								<table class="table table-striped">
									  <thead>
									    <tr>
									      <th scope="col">#</th>
									      <th scope="col">Thời gian</th>
									      <th scope="col">Hình ảnh</th>
									      <th scope="col">Mô tả</th>
									      <th scope="col">#</th>
									    </tr>
									  </thead>
									  <tbody>
									    <c:forEach items="${list}" var="item" varStatus="i">
									    	<tr> 
										      <th scope="row">${i.index + 1}</th>
										      <td>${item.createDate}</td>
										      <td><img width="80px" height="80px" alt="" src='<c:url value="${item.imgUrl}"></c:url>'></td>
										      <td>${item.description}</td>
										      <td>
										      	<a href="javascript:void(0)" onclick="deleteItem(${item.id})" class="btn btn-danger"><i class="glyphicon glyphicon-trash"></i></a>
										      </td>
									   		 </tr>
									    </c:forEach>	
									  </tbody>
								</table>
							        
							    </div>							   							   
								</div>
							</div>

		</div>
	</div>
</div>
<script type="text/javascript">
	$(document).ready(function(){
		$('#submitForm').submit(function(){
			var multipartFile = $('#multipartFile').val();
			var description = $('#description').val();
			if(description==''){
				var alertShow = $('#alertshow');
				var str = '<div class="alert alert-danger alert-dismissible  show" role="alert">' 
								+	'Vui lòng điền đầy đủ.'
								+	'<button type="button" class="close" data-dismiss="alert" aria-label="Close">'
								+	' <span aria-hidden="true">&times;</span>'
								+	'</button>'					
						+	'</div>'		
				alertShow.html(str);
				return false;
			}
		});
		
	});
	 

	function deleteItem(id){
		if(confirm("Bạn có chắc chắn xóa nó không ?")){
			location.href="<c:url value='/history/delete/'/>"+id+"?idMain="+${maintenance.id};
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
		$('#maintenancelist').parents("li").addClass('active').siblings().removeClass("active");
		$('#maintenancelist').addClass('current-page').siblings().removeClass("current-page");
		$('#maintenancelist').parents().show();
	});
	

	
</script>

				

