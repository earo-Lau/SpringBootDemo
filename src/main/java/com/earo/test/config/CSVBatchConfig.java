package com.earo.test.config;

import com.earo.test.model.Person;
import com.earo.test.service.batch.CSVBeanValidator;
import com.earo.test.service.batch.CSVItemProcessor;
import com.earo.test.service.batch.CSVJobListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.validator.Validator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**
 * Created by lauearo on 16/05/2017.
 */
@Configuration
@EnableBatchProcessing
public class CSVBatchConfig {

    /***
     * Read file via FlatFileItemReader
     * @return
     * @throws Exception
     */
    /*
    @Bean
    public ItemReader<Person> reader() throws Exception{
        return flatReader("people.csv");
    }
    */

    @Bean
    @StepScope
    public FlatFileItemReader<Person> reader(@Value("#{jobParameters['input.file.name']}") String pathToFile) throws Exception{
        FlatFileItemReader<Person> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource(pathToFile));
        reader.setLineMapper(new DefaultLineMapper<Person>(){{
            setLineTokenizer(new DelimitedLineTokenizer(){{
                setNames(new String[]{ "name", "age", "address", "nation" });
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Person>(){{
                setTargetType(Person.class);
            }});
        }});

        return reader;
    }

    /***
     * Process the item after read
     * @return
     */
    @Bean
    public ItemProcessor<Person, Person> processor(){
        CSVItemProcessor processor = new CSVItemProcessor();    //use custom processor
        processor.setValidator(csvBeanValidator());     //set custom validator
        return processor;
    }

    /***
     * write items after process to datasource using JdbcBatchItemWriter
     * @param dataSource    bean from spring container
     * @return
     */
    @Bean
    public ItemWriter<Person> writer(DataSource dataSource){
        JdbcBatchItemWriter<Person> writer = new JdbcBatchItemWriter<>();
        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        String sql = "insert into person " + "(name, age, address, nation) " + "values(:name, :age, :address, :nation)";
        writer.setSql(sql);     //execute sql command
        writer.setDataSource(dataSource);

        return writer;
    }

    /***
     * create JobRepository to launch job
     * @param dataSource
     * @param platformTransactionManager    from spring container
     * @return
     * @throws Exception
     */
    @Bean
    public JobRepository jobRepository(DataSource dataSource, PlatformTransactionManager platformTransactionManager)
            throws Exception {
        JobRepositoryFactoryBean jobRepositoryFactoryBean = new JobRepositoryFactoryBean();
        jobRepositoryFactoryBean.setDataSource(dataSource);
        jobRepositoryFactoryBean.setTransactionManager(platformTransactionManager);
        jobRepositoryFactoryBean.setDatabaseType("mysql");
        return jobRepositoryFactoryBean.getObject();
    }

    /***
     * SimpleJobLauncher define to launch job automated
     * @param dataSource    bean from spring container
     * @param transactionManager    bean from spring container
     * @return
     * @throws Exception
     */
    @Bean
    public SimpleJobLauncher jobLauncher(DataSource dataSource, PlatformTransactionManager transactionManager)
            throws Exception{
        SimpleJobLauncher jobLauncher = new SimpleJobLauncher();
        jobLauncher.setJobRepository(jobRepository(dataSource, transactionManager));
        return jobLauncher;
    }

    /***
     * Job Defined
     * @param jobs  bean from spring container
     * @param s1    step to run in this job
     * @return
     */
    @Bean
    public Job importJob(JobBuilderFactory jobs, Step s1){
        return jobs.get("importJob")
                .incrementer(new RunIdIncrementer())
                .flow(s1)    //set up flow step
                .end()
                .listener(csvJobListener()) //set up listener
                .build();
    }

    /***
     * Step Defined, create step to run in job
     * @param stepBuilderFactory    bean from spring container
     * @param reader    bean from spring container
     * @param writer    bean from spring container
     * @param processor bean from spring container
     * @return
     */
    /*
    @Bean(name = "stepAuto")
    @ConditionalOnBean({ ItemReader.class })
    public Step stepAuto(StepBuilderFactory stepBuilderFactory,
                      ItemReader<Person> reader,
                      ItemWriter<Person> writer,
                      ItemProcessor<Person, Person> processor){

        return stepBuilderFactory
                .get("stepAuto")
                .<Person, Person>chunk(65000)   //batch process 650000 recode per submit
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }
    */

    @Bean(name = "step1")
    public Step step1(StepBuilderFactory stepBuilderFactory,
                      FlatFileItemReader<Person> reader,
                      ItemWriter<Person> writer,
                      ItemProcessor<Person, Person> processor){

        return stepBuilderFactory
                .get("step1")
                .<Person, Person>chunk(65000)   //batch process 650000 recode per submit
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public CSVJobListener csvJobListener(){
        return new CSVJobListener();
    }

    @Bean
    public Validator<Person> csvBeanValidator(){
        return new CSVBeanValidator<>();
    }
}
