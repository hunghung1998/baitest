<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Insert title here</title>
        <title>Search Visitor</title>
        <link rel="stylesheet" type="text/css" href="css/java_tech_css.css">
    </head>
    <body>

        <div id="container">
            <div id="header">
                <img src="images/customLogo.jpg" style="width: 100%" />
            </div>
            <fieldset>
                <h3>Search Visitor</h3>
                <div id="searchNav">
                    <form action="MyServlet" method="get">
                        Firstname: <input class="inputText_search" type="text"
                                          name="firstName" /> <input type="submit" value="Search.." />
                        (Type First name vistor and Click Search button)
                    </form>
                </div>
            </fieldset>

            <div id="content">
                <table id="tableSearch">
                    <tr>
                        <th id="ths">Firstname</th>
                        <th id="ths">Lastname</th>
                        <th id="ths">Gender</th>
                        <th id="ths">Telephone</th>
                        <th id="ths">You're in</th>
                        <th id="ths">Hobbies</th>
                        <th id="ths" class="des">Description</th>
                    </tr>
              
                </table>
            </div>

        </div>
    </body>
</html>