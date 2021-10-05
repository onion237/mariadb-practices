-- subquery

-- 현재 Fai Bale이 근무하는 부서에서 근무하는 직원의 사번, 전체 이름을 출력해보세요.
select *
from dept_emp
where to_date = '9999-01-01' 
	and dept_no = (select dept_no 
					from dept_emp 
                    where to_date = '9999-01-01' 
                    and emp_no in (select emp_no 
									from employees 
                                    where first_name = 'Fai' 
                                    and last_name = 'Bale'));

-- 단일행 연산자는 서브쿼리의 결과가 1 row여야 함
-- 현재 전체 사원의 평균 연봉보다 적은 급여를 받는 사원의 이름, 급여를 출력하세요.
select first_name, salary
from employees e 
	join salaries s on e.emp_no = s.emp_no
where to_date = '9999-01-01' 
	and salary < (select avg(salary) from salaries where to_date = '9999-01-01')
order by salary desc;

-- 현재 가장 적은 평균 급여의 직책과 평균급여를 출력하세요.
select min(average)
from (select title, avg(salary) average
		from titles t 
			join salaries s 
            on t.emp_no = s.emp_no
		where t.to_date = '9999-01-01' 
			and s.to_date = '9999-01-01'
		group by title) avgs;

select title, avg(salary) average
from titles t join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01' and s.to_date = '9999-01-01'
group by title
order by average
limit 1;

select title, avg(salary)
from titles t join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by title
having avg(salary) = 
	(select min(average)
	from (select title, avg(salary) average
		from titles t 
			join salaries s 
            on t.emp_no = s.emp_no
		where t.to_date = '9999-01-01' 
			and s.to_date = '9999-01-01'
		group by title) avgs);
        
-- 복수행 연산자 : in, not in, any, all

-- 현재 급여가 50000 이상인 직원의 이름을 출력하세요
select first_name, salary
from employees e join salaries s on e.emp_no = s.emp_no
where to_date = '9999-01-01' and salary >= 50000;

select first_name
from employees e
where emp_no in (select emp_no from salaries where to_date = '9999-01-01' and salary >= 50000);

-- 각 부서별 최고 월급을 받는 직원의 이름과 월급
select first_name, salary, dept_name
from employees e 
	join salaries s on e.emp_no = s.emp_no
	join dept_emp de on e.emp_no = de.emp_no  
    join departments d on de.dept_no = d.dept_no
where (de.dept_no, salary) in (select de.dept_no, max(salary) as sal
									from dept_emp de join salaries s on de.emp_no = s.emp_no
									where de.to_date = '9999-01-01' and s.to_date = '9999-01-01'
									group by de.dept_no)
		and s.to_date = '9999-01-01' 
		and de.to_date = '9999-01-01';