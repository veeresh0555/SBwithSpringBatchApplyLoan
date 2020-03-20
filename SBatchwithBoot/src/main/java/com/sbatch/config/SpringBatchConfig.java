package com.sbatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.sbatch.model.User;

@EnableBatchProcessing
@Configuration
public class SpringBatchConfig {

	
	@Autowired
	StepBuilderFactory sbf;
	
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private DataSource datasource;
	
	@Bean
	public FlatFileItemReader<User> flatFileitemreader(){
		FlatFileItemReader<User> fileItemreader=new FlatFileItemReader<User>();
		fileItemreader.setResource(new ClassPathResource("user.csv"));//Read file
		fileItemreader.setLineMapper(new DefaultLineMapper<User>() {{
				setLineTokenizer(new DelimitedLineTokenizer() {{
						setNames(new String[] {"name","gender","age","pan","aadhar","salary"});
					}});
					setFieldSetMapper(new BeanWrapperFieldSetMapper<User>() {{
					setTargetType(User.class);
					}});
				
			}});
		return fileItemreader;
	}
	
	
	@Bean
	public JdbcBatchItemWriter<User> itemWriter(){
		JdbcBatchItemWriter<User> itemwriter=new JdbcBatchItemWriter<User>();
		itemwriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<User>());
		itemwriter.setSql("insert into user(name,gender,age,pan,aadhar,salary) values(:name,:gender,:age,:pan,:aadhar,:salary)");
		itemwriter.setDataSource(datasource);
		return itemwriter;
	}
	
	
	@Bean
    public Job readuserCSV() {
        return jobBuilderFactory
                .get("Job")
                .incrementer(new RunIdIncrementer())
                .flow(step1())
                .end()
                .build();
    }
	
	
	
	@Bean
	public Step step1() {//here i skip processor->sbf.get("step1").<User,User>chunk(10).reader(flatFileitemreader()).processor(processor).writer(ItemWriter()).build();
		return sbf.get("step1").<User,User>chunk(10).reader(flatFileitemreader()).writer(itemWriter()).build();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
