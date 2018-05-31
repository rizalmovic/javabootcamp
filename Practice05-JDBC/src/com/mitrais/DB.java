package com.mitrais;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DB {
    private Connection connect = null;
    private ResultSet resultSet = null;

    public DB (String host, String database, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            this.connect = DriverManager.getConnection("jdbc:mysql://" + host + "/" + database + "?user=" + username + "&password=" + password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Player> all() throws Exception {
        Statement statement = connect.createStatement();
        resultSet = statement.executeQuery("select * from test.dummy");
        List<Player> players = new ArrayList<Player>();

        while (resultSet.next()) {
            players.add(
                    new Player(
                            resultSet.getString("name"),
                            resultSet.getString("email"),
                            resultSet.getInt("score"),
                            resultSet.getInt("id")
                    )
            );
        }
        return players;
    }

    public Player get(int id) throws Exception {
        Statement statement = connect.createStatement();
        resultSet = statement.executeQuery("select * from test.dummy where id = " + id + " limit 1");
        while (resultSet.next()) {
            return new Player(
                    resultSet.getString("name"),
                    resultSet.getString("email"),
                    resultSet.getInt("score")
            );
        }
        return null;
    }

    public Boolean insert(Player player) throws Exception {
        PreparedStatement preparedStatement = connect.prepareStatement("insert into test.dummy values (default, ?, ?, ?)");
        preparedStatement.setString(1, player.getName());
        preparedStatement.setString(2, player.getEmail());
        preparedStatement.setInt(3, player.getScore());
        return preparedStatement.executeUpdate() != 0;
    }

    public Boolean update(Player player, int id) throws Exception {
        PreparedStatement preparedStatement = connect.prepareStatement("update test.dummy set name = ?, email = ?, score = ? where id = ?");
        preparedStatement.setString(1, player.getName());
        preparedStatement.setString(2, player.getEmail());
        preparedStatement.setInt(3, player.getScore());
        preparedStatement.setInt(4, id);
        return preparedStatement.executeUpdate() != 0;
    }

    public Boolean delete(int id) throws Exception {
        PreparedStatement preparedStatement = connect.prepareStatement("delete from test.dummy where id = ?");
        preparedStatement.setInt(1, id);
        return preparedStatement.executeUpdate() != 0;
    }

    public void close() {
        try {
            if(resultSet !=  null) resultSet.close();
            if(connect != null) connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertBatch(List<Player> players) throws Exception {
        PreparedStatement preparedStatement = connect.prepareStatement("insert into test.dummy values (default, ?, ?, ?)");
        players.forEach(x -> {
            try {
                // statement.addBatch("insert into test.dummy values (default, " + x.getName() + ", " + x.getEmail() +", " + x.getScore().toString() +")");
                preparedStatement.setString(1, x.getName());
                preparedStatement.setString(2, x.getEmail());
                preparedStatement.setInt(3, x.getScore());
                preparedStatement.addBatch();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        preparedStatement.executeBatch();
    }
}
