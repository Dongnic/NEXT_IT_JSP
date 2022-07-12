package com.study.common.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Aspect
public class CommonParameter {
	
	private Logger logger= LoggerFactory.getLogger(this.getClass());
	// study4_3에 AOP적용하기. web에 있는 모든 클래스의 모든 메소드 중에서 파라미터가 1개 이상인거 
	@Pointcut("execution(public * com.study.*.web..*(*,..))")
	private void controllerTarget() {}
	
	@Around("controllerTarget()")
	public Object showParameter(ProceedingJoinPoint joinPoint) throws Throwable{
		Object[] args= joinPoint.getArgs();
		logger.info("클래스 이름 : {}", joinPoint.getTarget().getClass().getSimpleName());
		logger.info("클래스 이름 : {}", joinPoint.getSignature().getName());
		// 파라미터가 0일 때도 null을 return안함, 길이가 0인 배열을 return
		// 그리고 여기서는 파라미터가 1개 이상일 때만 실행되는 메소드
		for(Object obj:args) {
			//System.out.println(obj.toString());
			// model은 데이터가 너무 많아서 내가 원하는 파라미터(searchVO, boNo) 등을 보기가 힘들다 model을 제외해보자
			if(obj==null) {
				logger.info(" {} : {}",obj);
			}else if(obj.getClass().getSimpleName().equals("BindingAwareModelMap")) {
				logger.info(" {} : {}",obj.getClass().getSimpleName()+" : "+ obj.toString());
			}
			
		}
		System.out.println("파라미터 끝 ");
		try {
			Object result= joinPoint.proceed();
			return result;
		}finally {
			System.out.println("잘 걸렸나?");
		}
	}
}
