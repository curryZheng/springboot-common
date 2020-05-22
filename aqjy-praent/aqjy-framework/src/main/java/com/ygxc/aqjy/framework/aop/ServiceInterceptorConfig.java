package com.ygxc.aqjy.framework.aop;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.UUID;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import com.ygxc.aqjy.common.constant.BConst;
import com.ygxc.aqjy.common.exception.YgxcBusinessException;
import com.ygxc.aqjy.common.utils.JsonUtil;
import com.ygxc.aqjy.common.utils.StringUtil;
import com.ygxc.aqjy.framework.annotation.AqjyValidate;
/**
 * service拦截器
 * 
 * @author leiZheng
 * @date 2019年6月21日
 */
@Configuration
@Aspect
@Order(999)
public class ServiceInterceptorConfig {

	private static Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

	protected Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 切点
	 */
	@Pointcut("execution(* com.ygxc.aqjy.service.impl.*.*(..))")
	public void executeService() {
	}

	/**
	 * 环绕通知
	 * 
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("executeService()")
	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long currTime = System.currentTimeMillis();
		Signature signature = pjp.getSignature();
		String sId = UUID.randomUUID().toString();
		Method method = ((MethodSignature)signature).getMethod(); 
		
		String methodName = StringUtil.defaultString(signature.getDeclaringTypeName()) + BConst.PERIOD
				+ StringUtil.defaultString(signature.getName());
		Object result = null;
		// 入参转json
		String argsJson = JsonUtil.toJsonLog(pjp.getArgs());
		logger.info("[sid : {}] begin call service [method : {}] [args : {}]", sId, methodName, argsJson);
			if(method.isAnnotationPresent(AqjyValidate.class)) {
				validate(pjp);
			}
		// 执行方法
		result = pjp.proceed();		
    	logger.info("[sid : {}] end call service [method : {}] [callTime : {}]", sId, methodName,  System.currentTimeMillis() - currTime);	
		return result;
	}

	/**
	 * 参数自动验证
	 * 
	 * @param point
	 * @return
	 * @throws Throwable
	 */
	private void validate(ProceedingJoinPoint point) throws Throwable {
		// 参数验证
		Object[] args = point.getArgs();
		if (args != null && args.length != 0) {
			for (int i = 0; i < args.length; i++) {
				if(args[i]!=null) {
					// 验证
					Set<ConstraintViolation<Object>> constraintViolations = validator.validate(args[i]);
					if (constraintViolations != null && constraintViolations.size() > 0) {
						ConstraintViolation<Object> violation = constraintViolations.iterator().next();
						throw new YgxcBusinessException(violation.getPropertyPath()+violation.getMessage());
					}
				}		
			}
		}

	}
	
	

}
