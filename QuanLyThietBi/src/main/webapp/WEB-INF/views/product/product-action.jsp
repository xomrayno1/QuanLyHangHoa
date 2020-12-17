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
									<form:form servletRelativeAction="/product/save" enctype="multipart/form-data"  modelAttribute="submitForm" method="POST" cssClass="form-horizontal form-label-left">
										<form:hidden path="id"/>
										<form:hidden path="activeFlag"/>
										<form:hidden path="createDate"/>
										<form:hidden path="updateDate"/>
										<form:hidden path="imageUrl"/>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Tên sản phẩm<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:input path="name" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="name" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Mã sản phẩm<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<form:input path="code" cssClass="form-control" readonly="${viewOnly}"/>
												<div class="has-error">
													<form:errors path="code" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<c:if test="${!viewOnly}">
											<div class="item form-group">
												<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Hình ảnh<span class="required">*</span>
												</label>
												<div class="col-md-6 col-sm-6 ">												
													<form:input type="file" path="multipartFile" cssClass="form-control" readonly="${viewOnly}"/>
													<div class="has-error">
														<form:errors path="multipartFile" cssClass="help-block"/>
													</div>
												</div>
											</div>
										</c:if> 
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Danh mục<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="idCate" cssClass="form-control">
															<form:option value="0">Chọn danh mục</form:option>
															<c:forEach items="${listCate}" var="item">
																<form:option value="${item.id}">${item.name}</form:option>
															</c:forEach>
														</form:select>
													</c:when>
													<c:otherwise>
														<form:input path="categoryDTO.name" cssClass="form-control" readonly="${viewOnly}"/>
													</c:otherwise>
												</c:choose>
												<div class="has-error">
													<form:errors path="idCate" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="name">Hãng sản xuất<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">												
												<c:choose>
													<c:when test="${!viewOnly}">
														<form:select path="brandId" cssClass="form-control">
															<form:option value="0">Chọn hãng sản xuất</form:option>
															<c:forEach items="${listBrand}" var="item">
																<form:option value="${item.id}">${item.name}</form:option>
															</c:forEach>
														</form:select>
													</c:when>
													<c:otherwise>
														<form:input path="brandName" cssClass="form-control" readonly="${viewOnly}"/>
													</c:otherwise>
												</c:choose>
												<div class="has-error">
													<form:errors path="brandId" cssClass="help-block"/>
												</div>
											</div>
										</div>
										<div class="item form-group">
											<label class="col-form-label col-md-3 col-sm-3 label-align" for="description">Description<span class="required">*</span>
											</label>
											<div class="col-md-6 col-sm-6 ">
												<form:textarea path="description" cols="8" rows="8" cssClass="form-control" readonly="${viewOnly}"/>
											</div>
										</div>
										<div class="ln_solid"></div>
											<div class="item form-group">
												<div class="col-md-6 col-sm-6 col-xs-12 col-md-offset-3">
												<c:if test="${!viewOnly}">
													<button type="submit" class="btn btn-success"><i class="glyphicon glyphicon-ok-circle"></i> Submit</button>
													<button class="btn btn-primary" type="reset"><i class="glyphicon glyphicon-refresh"></i> Reset</button>	
												</c:if>			
												<a href='<c:url value="/product/list/1"></c:url>'><button class="btn btn-primary" type="button"><i class="glyphicon glyphicon-minus-sign"></i> Cancel</button></a>																					
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
			$('#productlist').addClass('current-page').siblings().removeClass("current-page");
			$("#productlist").parents("li").addClass("active").siblings().removeClass("active");
			$("#productlist").parents().show();			
		});	
	</script>