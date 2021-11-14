package testng;

import org.testng.annotations.Test;

public class Topic_04_Priority_Skip_Description {

	@Test(description = "JIRA#98374 - Create a new user and verify user created success")
	public void User_01_Create_New_User() {
		System.out.println("Run TC 01");
	}

	@Test(enabled = false)
	public void User_02_View_User() {
		System.out.println("Run TC 02");
	}

//	@Test
	public void User_03_Edit_User() {
		System.out.println("Run TC 03");
	}

	@Test
	public void User_04_Move_User() {
		System.out.println("Run TC 04");
	}

	@Test
	public void User_05_Delete_User() {
		System.out.println("Run TC 05");
	}
}
