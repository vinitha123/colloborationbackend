package com.niit.testCase;

import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.niit.backend.Date_Time;
import com.niit.dao.JobDAO;
import com.niit.model.Job;

public class TestJob 
{
	Logger log = LoggerFactory.getLogger(TestUser.class);
	
	@Autowired
	JobDAO jobDAO;
	
	@Autowired
	Job job;
	
	@Autowired
	AnnotationConfigApplicationContext context;
	
	public TestJob()
	{	
		context = new AnnotationConfigApplicationContext();
		context.scan("com.niit");
		context.refresh();

		jobDAO = (JobDAO) context.getBean("jobDAO");
		job = (Job) context.getBean("job");
	}
	@Test
	public void addJob()
	{
		job.setTitle("My Third Job");
		job.setUsername("manasa1234");
		job.setStatus('Y');
		job.setPosition("Developers");
		job.setCompany("Infotech");
		job.setDescription("Software Engineer reqd freshers from 2016/2017 batch. Prefered Branch : CS/ IT");
		job.setSalary("Rs. 3.5 LPA");
		job.setLocation("INDIA");
		job.setQ_10("60.0%");
		job.setQ_12("60.0%");
		job.setQ_ug("65.0%");
		
		Date_Time dt = new Date_Time();
		String date = dt.getDateTime();
		job.setDate(date);
		job.setVacancy(100);
		
		jobDAO.addJob(job);
		System.out.println("Job has been added");
	}
	@Test
	public void listJob()
	{
		List<Job> jobs = jobDAO.listJobs();
		int n = jobs.size();
		System.out.println("Size "+n);
		for(int i=0;i<n;i++)
		{
			System.out.println("Name -"+jobs.get(i).getTitle()+"\t Company"+jobs.get(i).getCompany()+"\t Position "+jobs.get(i).getPosition());
		}
	}
	
	public void deleteJob()
	{
		int id = 39;
		jobDAO.deleteJob(id);
		System.out.println("Job deleted");
	}
	@Test
	public void inValidate()
	{
		int id = 40;
		jobDAO.invalidateJob(id);
		System.out.println("Invalidate Success");
	}
	@Test
	public void getJob()
	{
		String name = "My Second Job";
		Job job = jobDAO.getJob(name);
		System.out.println("JOB Description");
		System.out.println("Name: "+job.getTitle()+"\t Company: "+job.getCompany()+"\t Position: "+job.getPosition());	
	}
	
	public static void main(String[] args) 
	{
		TestJob tj = new TestJob();
		tj.addJob();
		tj.listJob();
//		tj.deleteJob();
		tj.inValidate();
		tj.getJob();
	}
}