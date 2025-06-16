String accountNumberQuery = "SELECT accountNumber FROM accounts\ 
    WHERE account_owner_id = ?";

try {
    PreparedStatement statement = connection.prepareStatement(accountNumberQuery);
    statement.setInt(1, currentAccountOwnerId);
    ResultSet rs = statement.executeQuery();

    int accountNumber = rs.getInt("accountNumber");
    String nickname = request.getParameter("nickname");  //user-supplied nickname

    while (rs.next()) {
        String makePDFCommand = "/opt/account_tools/bin/make_account_pdf " +
            accountNumber + " \"" + nickname + "\"";
    
        Runtime.getRuntime().exec(makePDFCommand);

        this.addLinkToStatementPDF(accountNumber, nickname);
    }
}
catch (SQLException e) { ... }
catch (IOException e) { ... }
