package com.everyt.nextea.user;

public class UserException {
	public static class EmailAlreadyExists extends RuntimeException {
		private static final long serialVersionUID = 1776214932270896270L;
		
		public EmailAlreadyExists(String message) {
			super(message);
		}
	}
}
