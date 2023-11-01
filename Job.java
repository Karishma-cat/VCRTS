class Job {
	
	private final int client;
	private final int id;
	private final int duration;
	private final String deadline;
	private final int completionTime;
	
	public Job(int client, int id, int duration, String deadline, int completionTime) {
		this.id = id;
		this.duration = duration;
		this.client = client;
		this.deadline = deadline;
		this.completionTime = completionTime;
	}
	
	public int getClient() {
		return client;
	}
	
	public int getDuration() {
		return duration;
	}
	
	public int getId() {
		return id;
	}

	public String getDeadline() {
		return this.deadline;
	}

	public int getCompletionTime() {
		return this.completionTime;
	}
}

   