package assignment2;

/**Michael Masterson
 * E00942993
 * Assignment Two - RDBMS Practice Part 2
 * COSC 471 Section 0
 * Dr. Zhang
 * November 2, 2016
 */

import java.sql.*;
public class assignment2 
{
	public static void main(String args[])
	{
		//start connection to JDBC
		Connection c = null;
		Statement stmt = null;
		try{
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:C:/Sqlite/chinook.db");
			c.setAutoCommit(false);
			System.out.println("Opened database");

			//start creating queries, comment in
			stmt = c.createStatement();
			/**
			//1.) Find the name of artists with more than 30 tracks
			ResultSet rs = stmt.executeQuery("SELECT Name, COUNT(*)"
					+ 						 "FROM tracks, albums, artists"
					+ 						 "WHERE albums.AlbumId = tracks.AlbumId AND artists.ArtistId = ablums.ArtistId"
					+ 						 "GROUP BY artists.ArtistId, artists.Name"
					+ 						 "HAVING COUNT (*) >30");

			while(rs.next())
			{
				String Name = rs.getString("");

				System.out.println("Artist Name: " + Name);
				System.out.println();
			}*/

			/**
			//2.) Find the most popular Rock playlist, i.e. playlist(s) which contain
			//the largest number of Rock tracks
			ResultSet rs = stmt.executeQuery("SELECT PlaylistId, Name"
					+ "FROM playlists, playlist_track, tracks,media_types,genres"
					+ "WHERE media_types.Name LIKE'%audio%' AND playlists.PlaylistId = playlist_track.PlaylistId"
					+									   "AND playlist_track.TrackId = tracks.TrackId"
					+									   "AND tracks.MediaTypeId = media_types.MediaTypeId"
					+									   "AND tracks.GenreId = genres.GenreID AND genres.Name ='Rock'"
					+"GROUP BY PlaylistId, Name"
					+ "HAVING COUNT(*) = (SELECT MAX(aux.count)"
										  +"FROM(SELECT COUNT(*) as count"
										  	+	"FROM playlists, playlist_track, tracks,media_types,genres"
										  	+	"media_types.Name LIKE'%audio%' AND playlists.PlaylistId = playlist_track.PlaylistId"
											+	"		   AND playlist_track.TrackId = tracks.TrackId"
											+	"		   AND tracks.MediaTypeId = media_types.MediaTypeId"
											+	"		   AND tracks.GenreId = genres.GenreID AND genres.Name ='Rock'"
										  	+	"									GROUP BY P.PlaylistId )aux)");

			while(rs.next())
			{
				String name = rs.getString("");

				System.out.println("Most Popular Rock Playlist: " + name);
				System.out.println();
			}*/
			

			/**
			//3.) Find the playlist that contains most tracks by artists "Aerosmith" (no View)
			 ResultSet rs = stmt.executeQuery("SELECT Name, COUNT(*)"
					+ "FROM playlists AS P, playlist_track AS PL, tracks,albums,artists"
					+ "WHERE P.PlaylsitId = PL.PlaylistId AND PL.TrackId = tracks.TrackId AND albums.AlbumId = tracks.AlbumId"
					+									   "AND artists.ArtistId = albums.ArtistId AND artists.Name='AC/DC'"
					+"GROUP BY PlaylistId, Name"
					+ "HAVING COUNT(*) = (SELECT MAX(aux.count)"
					+					  "FROM(SELECT COUNT(*) as count"
					+					  		"FROM playlists AS P, playlist_track AS PL, tracks,tracks,albums,artists"
					+					  		"WHERE P.PlaylistId - PL.PlaylisteId AND PL.TrackId = tracks.TrackId AND ablums.AlbumId = tracks.AlbumId AND artists.ArtistId = albums.ArtistId AND artists.Name='AC/DC'"
					+							"GROUP BY P.PlaylistId) aux");


			while(rs.next())
			{
				String name = rs.getString("");

				System.out.println("Playlist with most tracks with Aerosmith: " + name);
				System.out.println();
			}*/

			/**
			//4.) Find the genre of tracks which is contained in the most playlist (no View)
			ResultSet rs = stmt.executeQuery("SELECT GenreID,Name, COUNT(Distinct PlaylistId"
											+ "FROM genres, tracks, playlist_track"
											+"WHERE genres.GenreId = tracks.GenreID AND tracks.TrackId = playlist_track.TrackId"
											+"GROUP BY genres.GenreId, genres.Name"
											+"HAVING COUNT (DISTINCT PlaylistId) = (SELECT MAX(aux.count)"
											+										"FROM(SELECT COUNT(DISTINCT PlaylistId) as count"
											+										"FROM genres, tracks, playlist_track"
											+										"WHERE genres.GenreId = tracks.GenreID AND tracks.TrackId = playlist_track.TrackId"
											+										"GROUP BY genres.GenreId, genres.Name) aux");

			while(rs.next())
			{
				String name = rs.getString("");

				System.out.println("Genre with most tracks: " + name);
				System.out.println();
			}*/	
			 

			/**
			//5.) Find the number of employees live in the same city with each customer
			ResultSet rs = stmt.executeQuery("SELECT EmployeeId, City"
					+ "FROM customers, employees"
					+ "WHERE EmployeeId = City"
					+ "GROUP BY City"
					+ "COUNT (*)");

			while(rs.next())
			{
				String City = rs.getString("");

				System.out.println("Employees in City: " + City);
				System.out.println();
			}*/

			/**
			//6.) Find artist has the most "Rocks" and "Metal" tracks combined
			ResultSet rs = stmt.executeQuery("SELECT artists, genres"
											+ "FROM genres, artists"
											+ "WHERE artists == genres.Name ='Rock' AND genres.Name ='Metal'");
					

			while(rs.next())
			{
				String art = rs.getString("");

				System.out.println("Artist with Metal and Rocks songs: " + art);
				System.out.println();
			}*/

			/**
			//7.) Find MPEG/MPEG4 tracks which have a length longer than the
			// average length of all the MPEG/MPEG4 tracks
			ResultSet rs = stmt.executeQuery("SELECT Name AS name"
					+ "FROM tracks, media_types"
					+ "WHERE tracks.MediaTypeId = MediaTypeId AND media_types.Name LIKE '%audio%'"
					+										 "AND tracks.Milliseconds > (SELECT AVG(Milliseconds)"
					+										 						   "FROM tracks, media_types"
					+										 						   "WHERE tracks.MediaTypeId = media_types.MediaTypeId AND media_types.Name LIKE'%audio%')");

			while(rs.next())
			{
				String Name = rs.getString("");

				System.out.println("MPEG Song: " + Name);
				System.out.println();
			}*/

			/**
			//8.) Find the name and phone of the third oldest (age) employee
			ResultSet rs = stmt.executeQuery("SELECT a.Name,a.Age, a.Phone"
					+ "FROM employees a"
					+ "WHERE 3= (SELECT COUNT(DISTINCT(b.Age,b.Phone))"
					+			"FROM employees b"
					+			"WHERE a.Age <= b.Age AND a.Phone <= b.Phone");

			while(rs.next())
			{
				String name = rs.getString("");
				String phone = rs.getString("");

				System.out.println("Name: " + name);
				System.out.println("Phone: " + phone);
				System.out.println();
			}*/
			rs.close();
			stmt.close();
			c.close();
		}catch (Exception e)
		{
			System.out.println(e.getClass().getName()+": "+e.getMessage());
			System.exit(0);
		}
		System.out.println("opened");
	}
}