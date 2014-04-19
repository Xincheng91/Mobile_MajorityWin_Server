package com.cmu.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cmu.bean.RoomBean;
import com.cmu.service.RoomService;

/**
 * Servlet implementation class CreateRoom
 */
@WebServlet("/CreateRoom")
public class CreateRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateRoom() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int number = new Random().nextInt(1000);
		String roomID = Integer.toString(number);
		String username = request.getParameter("username");
		ArrayList<String> people = new ArrayList<String>();
		people.add(username);
		RoomBean rb = new RoomBean(roomID, 5, people);
		if(RoomService.isRoomExist(roomID)){
			response.getOutputStream().write(new String().getBytes());
		}else{
			RoomService.createRoom(roomID, rb);
			response.getOutputStream().write(roomID.getBytes());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

}
