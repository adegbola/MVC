<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Items Page</title>
        <!-- Bootstrap core CSS -->
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body>
        <div class="container">
            <h1>Vending Machine</h1>
            <hr/>
            <div class="row">
                <div class="col-md-8">
                    <c:forEach var="currentItem" items="${items}">
                        <div class="col-md-4">
                            <form class="form-horizontal"  method="GET" action="selectItem">
                                <button type="submit" class="btn btn-default" value="${currentItem.itemId}" name="itemId"><c:out value="${currentItem.getItemId()}"/><br>
                                    <c:out value=" ${currentItem.itemName}"/><br>
                                    Quantity:<c:out value="${currentItem.numOfItems}"/><br>
                                    $<c:out value="${currentItem.itemPrice}"/><br>

                                </button></form></div>

                    </c:forEach>
                </div>



                <div class="col-md-4">



                    <div class="form-group" style="text-align: center ">
                        <label for="inputAmount">
                            Total $ input</label>
                        <input type="text " class="form-control" value="${total}" readonly />
                    </div>

                    <form class="form-horizontal"  method="POST" action="addMoney">
                        <button type="submit" class="btn btn-default amount-adding-btn" name="dollarAmount" value="1.00">Add Dollar
                        </button>
                    </form>
                    <form class="form-horizontal"  method="POST" action="addMoney">
                        <button type="submit" class="btn btn-default amount-adding-btn" name="quarterAmount" value="0.25">Add Quarter
                        </button>
                    </form>
                    <br>

                    <form class="form-horizontal"  method="POST" action="addMoney">
                        <button type="submit" class="btn btn-default amount-adding-btn" name="dimeAmount" value="0.10">Add Dime
                        </button>
                    </form>

                    <form class="form-horizontal"  method="POST" action="addMoney">
                        <button type="submit" class="btn btn-default amount-adding-btn" name="nickelAmount" value="0.05">Add Nickel
                        </button>
                    </form>
                    <hr/>

                    <div class="form-group" method="GET" role="form" action="purchaseItem?itemid=${itemId}" style="text-align: center">
                        <label for="Message">Messages</label>
                        <input type="text" class="form-control" value="${Message}" style="text-align: center" readonly/>

                        <label for="input-Items">Items</label>
                        <input type="text" class="form-context" value="${itemId}"style="width:90px" id="input-Items" readonly/>
                    </div>

                    <form method="POST" action="purchaseItem">
                        <div class="form-group" style="text-align: center">
                            <input type="hidden" class="form-control" id="itemBought" name="itemBought" value="${itemId}">
                            <button type="submit" class="btn btn-default" id="Btn-Make-Purchase" style="width:120px;">Make Purchase
                            </button><br>
                        </div>
                    </form>

                    <hr/>
                    <p style="font-size: 20px; text-align: center;">Change</p>
                    <input type="text" class="form-control" value="D:${d} Q: ${q} D: ${d} N: ${n} P: ${p}" style="text-align: center; height: 40px;" readonly/><br/>
                    <br/>

                    <form class="form-horizontal" method="POST" role="form" action="getChange" style="text-align: center;">
                        <button type="submit" class="btn btn-default" style="width: 100px;">Get Change</button>
                    </form>
                </div>
            </div>

        </div>

        <!-- Placed at the end of the document so the pages load faster -->
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </div>
</body>
</html>

