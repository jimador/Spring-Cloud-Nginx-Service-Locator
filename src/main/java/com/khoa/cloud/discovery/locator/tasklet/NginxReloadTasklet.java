package com.khoa.cloud.discovery.locator.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@StepScope
@Component(NginxReloadTasklet.COMPONENT_NAME)
public class NginxReloadTasklet implements Tasklet {
    public final static String COMPONENT_NAME = "nginxReloadTasklet";

    @Value("${nginx.where}")
    private String nginxLocation;

    @Override
    public RepeatStatus execute(@NonNull StepContribution stepContribution, @NonNull ChunkContext chunkContext) throws Exception {
        var processBuilder = new ProcessBuilder();

        processBuilder.command("/bin/sh", "-c", nginxLocation + " -s reload");

        processBuilder.start();

        return RepeatStatus.FINISHED;
    }
}
