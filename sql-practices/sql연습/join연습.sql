-- 현재 회사 상황을 반영한 직원별 근무부서를 사번, 이름, 근무 부서로 출력해보세요
select e.emp_no, first_name, d.dept_name
from employees e 
	join dept_emp de on e.emp_no = de.emp_no
	join departments d on de.dept_no = d.dept_no 			
where to_date = '9999-01-01';

-- 현재 회사에서 지급되고 있는 급여체계를 반영한 결과를 출력하세요
-- 사번, 이름, 연봉 형태로 출력하세요
select e.emp_no, first_name, salary
from employees e
	join salaries s on e.emp_no = s.emp_no
where to_date = '9999-01-01'
order by salary desc;

-- 현재 직책별로 평균 연봉과 인원수를 구하되 직책별로 인원이 100명 이상인 직책만 출력
select title, avg(salary), count(*) as cnt
from titles t join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01' and s.to_date = '9999-01-01'
group by title
having cnt >= 100
order by avg(salary) desc;

-- 현재 부서별로 현재 직책이 Engineer인 직원들에 대해서만 평균급여를 구하세요
select d.dept_name, avg(salary), count(*)
from dept_emp de 
	join titles t on de.emp_no = t.emp_no
    join salaries s on t.emp_no = s.emp_no
    join departments d on de.dept_no = d.dept_no
where de.to_date = '9999-01-01' 
	and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
    and title = 'Engineer'
group by de.dept_no;

-- 현재 직책별로 급여의 총합을 구하되 Engineer직책은 제외하세요
-- 단, 총합이 2,000,000,000이상인 직책만 나타내며 급여총합에 대해서 내림차순으로 정렳하세요
select title, sum(salary) as sum_salary
from titles t join salaries s on t.emp_no = s.emp_no
where title != 'Engineer'
	and t.to_date = '9999-01-01'
    and s.to_date = '9999-01-01'
group by title
having sum_salary > 2000000000
order by sum_salary desc;