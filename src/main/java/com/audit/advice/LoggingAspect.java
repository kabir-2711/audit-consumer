package com.audit.advice;

import java.util.Collections;
import java.util.stream.IntStream;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import com.utilities.log.Log;

/**
 * Aspect for logging execution of Service and Repository components. This
 * Aspect is responsible for logging method entries, exit, errors and returned
 * values.
 * 
 * 
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html">
 *      Component </a>
 * @see <a href =
 *      "https://docs.spring.io/spring-framework/docs/4.3.15.RELEASE/spring-framework-reference/html/aop.html">
 *      Aspect </a>
 * @author Kabir Akware
 */
@Aspect
@Component
public class LoggingAspect {

	/**
	 * PointCut that matches all Services, Components, RestController and
	 * Configuration for logging.
	 * 
	 * This PointCut expression matches execution of any public methods specified
	 * within the package {@code com.encryption}
	 */
	@Pointcut("execution(* com.encryption..*(..)) && !execution (* org.springframework.data.repository..*(..))")
	public void beanPointCut() {
	}

	/**
	 * PointCut that matches all Controllers for error logging.
	 * 
	 * This PointCut expression matches execution of any public methods specified
	 * within the packages annotated with
	 * {@link org.springframework.web.bind.annotation.RestController},
	 */
	@Pointcut("within (@org.springframework.web.bind.annotation.RestController *)")
	public void errorBeanPointCut() {
	}

	/**
	 * Logs the execution of public methods in Spring Beans. This method runs before
	 * the execution of any public method in the specified packages and logs the
	 * method signature.
	 * 
	 * @param joinPoint Provides access to method details being executed
	 */
	@Before("beanPointCut()")
	public void logBefore(JoinPoint joinPoint) {

		String[] params = ((MethodSignature) joinPoint.getSignature()).getParameterNames();
		Object[] args = joinPoint.getArgs();

		Log.info(joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),
				"method started");

		Log.debug(joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),
                "method started with arguments: %s".formatted(
                        params.length != 0 ? String.join(", ", Collections.nCopies(params.length, "%s")) : null),
				IntStream.range(0, params.length).mapToObj(i -> params[i] + ":" + String.valueOf(args[i])).toArray());
	}

	/**
	 * Logs the return value of methods after execution. This method runs after a
	 * method successfully executes.
	 * 
	 * @param joinPoint Provides access to method details being executed
	 */
	@After("beanPointCut()")
	public void logAfter(JoinPoint joinPoint) {
		Log.info(joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),
				"method ended");
	}

	/**
	 * Logs the return value of methods after execution. This method runs after a
	 * method successfully returns and logs the return value.
	 * 
	 * @param joinPoint Provides access to method details being executed
	 * @param result    The value returned by a method
	 */
	@AfterReturning(pointcut = "beanPointCut()", returning = "result")
	public void logAfterReturning(JoinPoint joinPoint, Object result) {
		Log.debug(joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),
				"method ended returning: %s", result);
	}
	
	/**
	 * Logs the error thrown by methods after execution failure. This method runs
	 * after a method throws an exception and logs the exception details.
	 * 
	 * @param joinPoint Provides access to method details being executed
	 * @param e         The error thrown by a method
	 */
	@AfterThrowing(pointcut = "errorBeanPointCut()", throwing = "e")
	public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
		Log.error(joinPoint.getSignature().getDeclaringType().getSimpleName(), joinPoint.getSignature().getName(),
				"exception occured in the application:\n%s",
				ExceptionUtils.getStackTrace(e));
	}
}
