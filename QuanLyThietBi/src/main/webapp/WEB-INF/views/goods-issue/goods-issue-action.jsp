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
									<form:form servletRelativeAction="/goods-issue/save"  modelAttribute="submitForm" method="POST" cssClass="form-horizontal form-label-left">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/>
										<form:hidden path="createDate"/>
										<form:hidden path="updateDate"/>
										<form:hidden path="userDTO.id"/>
										<form:hidden path="type"/>
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
														<form:input path="productDTO.name" cssClass="form-control" readonly="true"/>
													</c:otherwise>
												</c:choose>
												<div class="has-error">
													<form:errors path="productId" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="price">Giá<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="price" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="price" type="number" min="1"  cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="quantity">Số lượng<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:input path="quantity" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="quantity"  type="number" min="1" cssClass="help-block"/>
												</div>
											</div>
										</div> 
										<div class="item form-group"> 
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="productId">Kho <span class="required">*</span>
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
														<form:input path="inventoryDTO.name" cssClass="form-control" readonly="true"/>
													</c:otherwise>
												</c:choose>
												<div class="has-error">
													<form:errors path="invenId" cssClass="help-block"/>
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
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="productId">Khách hàng <span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="cusId" cssClass="form-control">
															<form:option value="0">Chọn khách hàng</form:option>
															<c:forEach items="${listCustomer }" var="item">
																<form:option value="${item.id}">${item.name}</form:option>
															</c:forEach>
														</form:select>
													</c:when>
													<c:otherwise>
														<form:input path="cusName" cssClass="form-control" readonly="true"/>
													</c:otherwise>
												</c:choose>
												<div class="has-error">
													<form:errors path="cusId" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="ln_solid"></div>
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												<a href='<c:url value="/goods-issue/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
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
		$(document).ready(function(){
			$('#goodsissuelist').addClass('current-page').siblings().removeClass("current-page");
			$("#goodsissuelist").parents("li").addClass("active").siblings().removeClass("active");
			$("#goodsissuelist").parents().show();			
		});	
	</script>