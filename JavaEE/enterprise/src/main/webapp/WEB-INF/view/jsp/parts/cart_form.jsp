<%@ page import="xyz.solovev.enterprise.entity.Products" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="cart" scope="session" class="xyz.solovev.enterprise.controller.CartController"/>
<jsp:setProperty name="cart" property="*"/>
<%
    Map<Products, Integer> productsMap = cart.getProductsMap();
%>
<div class="container">
    <span>Total in Cart: <%=productsMap.size()%></span>

    <table id="cart" class="table table-hover table-condensed">
        <thead>
        <tr>
            <th style="width:50%">Product</th>
            <th style="width:10%">Price</th>
            <th style="width:8%">Quantity</th>
            <th style="width:22%" class="text-center">Subtotal</th>
            <th style="width:10%"></th>
        </tr>
        </thead>
        <tbody>
        <% for (Map.Entry<Products, Integer> p : productsMap.entrySet()) {%>
        <tr>
            <td data-th="Product">
                <div class="row">
                    <div class="col-sm-2 hidden-xs" style="padding-left: 0; padding-right:0"><img
                            src="http://placehold.it/100x100" alt="..." class="img-responsive"></div>
                    <div class="col-sm-11" style="padding-left: 0; padding-right:0">
                        <h4 class="nomargin"><%=p.getKey().getName()%>
                        </h4>
                        <p><%=p.getKey().getDescription()%>
                        </p>
                    </div>
                </div>
            </td>
            <td data-th="Price">
                <%=p.getKey().getPrice()%>
            </td>
            <td data-th="Quantity">
                <input class="form-control text-center" value="<%=p.getValue()%>" type="number">
            </td>

            <td data-th="Subtotal" class="text-center">
                <%=p.getValue() * p.getKey().getPrice()%>
            </td>

            <td class="actions" data-th="">
                <button class="btn btn-info btn-sm"><i class="fa fa-refresh"></i></button>
                <button class="btn btn-danger btn-sm"><i class="fa fa-trash-o"></i></button>
            </td>

        </tr>
        <%}%>
        </tbody>
        <tfoot>

        <tr>
            <td><a href="#" class="btn btn-warning"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
            <td colspan="2" class="hidden-xs"></td>
            <td class="hidden-xs text-center"><strong>Total: <%=cart.getTotal()%>
            </strong></td>
            <td><a href="#" class="btn btn-success btn-block">Checkout <i class="fa fa-angle-right"></i></a></td>
        </tr>
        </tfoot>
    </table>

</div>


