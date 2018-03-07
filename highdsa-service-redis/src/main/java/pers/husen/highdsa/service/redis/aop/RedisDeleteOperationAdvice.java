package pers.husen.highdsa.service.redis.aop;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import pers.husen.highdsa.common.aop.BaseSpringAspect;

/**
 * @Desc redis delete操作切面通知
 *
 * @Author 何明胜
 *
 * @Created at 2018年3月5日 上午8:37:13
 * 
 * @Version 1.0.0
 */
@Aspect
public class RedisDeleteOperationAdvice extends BaseSpringAspect{
	private static final Logger logger = LogManager.getLogger(RedisDeleteOperationAdvice.class.getName());

	@Override
	@Pointcut("execution(* pers.husen.highdsa.service.redis.RedisOperationImpl.del*(..)) || execution(* pers.husen.highdsa.service.redis.RedisOperationImpl.remove*(..)) || execution(* pers.husen.highdsa.service.redis.RedisOperationImpl.flush*(..))")
	protected void aspectJMethod() {
	}

	@Override
	@Around("aspectJMethod()")
	public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
		Object key = getArgs(joinPoint)[0];
		String methodName = getAimMethodName(joinPoint);
		
		Object retval = joinPoint.proceed();

		logger.info("<{}> redis cache [delete], key={},reply={}", methodName, key, retval);

		return retval;
	}

	@Override
	public void doBefore(JoinPoint joinPoint) {
	}
	
	@Override
	public void doAfter(JoinPoint joinPoint) {
	}

	@Override
	public void doReturn(JoinPoint joinPoint, Object returnValue) {
	}

	@Override
	public void doThrowing(JoinPoint joinPoint, Exception e) {
	}
}