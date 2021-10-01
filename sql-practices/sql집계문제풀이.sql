-- 문제 1. 최고임금(salary)과 최저임금을 “최고임금, “최저임금”프로젝션 타이틀로 함께 출력해보세요. 두 임금의 차이는 얼마인가요? 함께 “최고임금 – 최저임금”이란 타이틀로 출력해보세요.
select max(salary) as 최고임금, min(salary) as 최저임금, max(salary) - min(salary) as '최고임금 - 최저임금'
from salaries;

-- 문제2. 마지막으로 신입사원이 들어온 날은 언제 입니까? 다음 형식으로 출력해주세요. 예) 2014년 07월 10일
select DATE_FORMAT(max(hire_date), '%Y년 %m월 %d일') 
from employees;

-- 문제3. 가장 오래 근속한 직원의 입사일은 언제인가요? 다음 형식으로 출력해주세요. 예) 2014년 07월 10일
select DATE_FORMAT(hire_date, '%Y년 %m월 %d일'), diff, a.emp_no
from (select emp_no, hire_date, if(max(to_date) = '9999-01-01', datediff(sysdate(),hire_date), datediff(max(to_date),  hire_date)) as diff from employees join salaries using(emp_no) group by emp_no) a
order by a.diff desc
limit 1;



select datediff(now(), '1985-01-01');
select * from salaries where emp_no = 110725;

-- select emp_no, hire_date, if(max(to_date) = '9999-01-01', curdate() - hire_date, max(to_date) - hire_date) as diff from employees join salaries using(emp_no) group by emp_no
-- order by diff desc;

-- 문제4. 현재 이 회사의 평균 연봉은 얼마입니까?
select avg(salary) as 평균연봉 from salaries where to_date = '9999-01-01';

-- 문제5. 현재 이 회사의 최고/최저 연봉은 얼마입니까?
select max(salary) as 최고연봉, min(salary) as 최저연봉 from salaries where to_date = '9999-01-01';


-- 문제6. 최고 어린 사원의 나이와 최 연장자의 나이는?
select min(birth_date) as '최연장자 생년월일', floor(DATEDIFF(now(), min(birth_date)) / 365) as '최연장자 나이', max(birth_date) as '최연소자 생년월일', floor(DATEDIFF(now(), max(birth_date)) / 365) as '최연소자 나이' from employees;



select * from salaries;
select emp_no, round(avg(salary))
from salaries
group by emp_no
having avg(salary) > 50000
order by avg(salary);

select emp_no, avg(salary), sum(salary)
from salaries
where emp_no = '10060';