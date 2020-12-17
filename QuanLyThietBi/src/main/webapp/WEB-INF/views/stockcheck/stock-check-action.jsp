<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"%>
	<div class="right_col" role="main">
				<div class="">
					<div class="page-title">
						<div class="title_left">
							<h3>${title}</h3>
						</div>
					</div>
		<div class="clearfix"></div>
					<div class="row">
						<div class="col-md-12 col-sm-12 ">
							<div class="x_panel">
								<div class="x_content">
									<br />
									<form:form servletRelativeAction="/stock-check/save"  modelAttribute="submitForm" method="POST" cssClass="form-horizontal form-label-left">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/>
										<form:hidden path="createDate"/>
										<form:hidden path="updateDate"/>
										<form:hidden path="userId"/>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="productId"> Kho <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="invenId" cssClass="form-control">
															<form:option value="0">Chọn kho</form:option>
															<c:forEach items="${listInven }" var="item">
																<form:option value="${item.id}">${item.name}</form:option>
															</c:forEach>
														</form:select>
													</c:when>
													<c:otherwise>
														<form:input path="invenName" cssClass="form-control" readonly="true"/>
													</c:otherwise>
												</c:choose>
												<div class="has-error">
													<form:errors path="invenId" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="productId">Sản phẩm <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="productId" cssClass="form-control">
															<form:option value="0">Chọn sản phẩm</form:option>
															<c:forEach items="${listProduct }" var="item">
																<form:option value="${item.id}">${item.name}</form:option>
															</c:forEach>
														</form:select>
													</c:when>
													<c:otherwise>
														<form:input path="productName" cssClass="form-control" readonly="true"/>
													</c:otherwise>
												</c:choose>
												<div class="has-error">
													<form:errors path="productId" cssClass="help-block"/>
												</div>
											</div>
										</div>
										 
										 
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="quantity">Tồn kho<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="quantity"  cssClass="form-control" readonly="true"/>
 
											</div>
										</div> 
										 <div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="actualQuantity">Tồn thực tế<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="actualQuantity"  type="number" min="0" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="actualQuantity"     cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="difference">Chênh lệch<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="difference" cssClass="form-control"    readonly="true"/>
												<div class="has-error">
													<form:errors path="difference"      cssClass="help-block"/>
												</div>
											</div>
										</div>  
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="description">Chi tiết<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:textarea path="description" row="4"  cssClass="form-control" readonly="${viewOnly}"/>
												 
											</div> 
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="dateCheck">Thời gian<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="dateCheck"   type="date"  cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="dateCheck"    cssClass="help-block"/>
												</div>
											</div>
										</div> 
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="status">Thời gian<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:select path="status" cssClass="form-control" disabled="${viewOnly}">
													 
													<form:option value="1">Đang kiểm kê</form:option>
													<form:option value="2">Đã kiểm kê</form:option>
												</form:select>
											</div>
										</div> 
										<div class="ln_solid"></div>
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												<a href='<c:url value="/stock-check/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
												</div>
											</div>
									</form:form>
								</div>
							</div>
						</div>
					</div>
		</div>
	</div>  
	<script type="text/javascript">
		$("#actualQuantity").change(function(){
			var actualQuantity = Number($(this).val());
			var result =   Number($("#quantity").val()) - actualQuantity ;
			$("#difference").val(result);
		});
		
		 
	
		 $("#invenId").change(function(){
			 var productId = $("#productId").val();
			 $.ajax({
				 url: "<c:url value='/api/v1/product-stock?proId="+productId+"&invenId="+$(this).val()+"'/>",
					type : 'GET',
					success : function(response){
						$("#quantity").val(response);
						var actualQuantity = Number($("#actualQuantity").val());
						var result =   response - actualQuantity ;
						$("#difference").val(result);
					}
			 })
		 })
		 
		 $("#productId").change(function(){
			 var invenId = $("#invenId").val();
			 $.ajax({
				 url: "<c:url value='/api/v1/product-stock?proId="+$(this).val()+"&invenId="+invenId+"'/>",
					type : 'GET',
					success : function(response){					
						$("#quantity").val(response);
						var actualQuantity = Number($("#actualQuantity").val());
						var result =  response - actualQuantity ;
						$("#difference").val(result);
					}
			 })
		 })
	</script>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#stockchecklist').addClass('current-page').siblings().removeClass("current-page");
			$("#stockchecklist").parents("li").addClass("active").siblings().removeClass("active");
			$("#stockchecklist").parents().show();			
		});	
	</script>