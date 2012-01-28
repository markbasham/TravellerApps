<html>
	<body>
		<h2>Greetings!!</h2>
		<p>This might actually be working then?</p>
		<p>The current time is <%=new java.util.Date()%> precisely </p>
		<p>New content ooh this gets added in quick</p>	
		<p>The current time is <%=new java.util.Date()%> precisely </p>

		<p>Test for gathering sector information</p>
		
		<form method="POST" action="SetServelet">

		Enter your subsector name : <input type="text" name="subsectorName" size="20" value="Example"><br>
		Enter Subsector List :<br>
		
		<textarea id="PlainTextIn" rows="8" cols="60" name="subsectorList"></textarea>
		<input type="submit" value="Submit" name="B1"><br>

		</form>
	</body>
</html>
