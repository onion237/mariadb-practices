-- 문제 1. 현재 급여가 많은 직원부터 직원의 사번, 이름, 그리고 연봉을 출력 하시오.
select emp_no, first_name, last_name, salary
from employees join salaries using(emp_no)
where to_date = '9999-01-01'
order by salary desc;

-- 문제2. 전체 사원의 사번, 이름, 현재 직책을 이름 순서로 출력하세요.
select emp_no, first_name, last_name, title
from employees join titles using(emp_no)
where to_date = '9999-01-01'
order by first_name, last_name;

-- 문제3. 전체 사원의 사번, 이름, 현재 부서를 이름 순서로 출력하세요..
select emp_no, first_name, last_name, dept_name
from employees join dept_emp using(emp_no)
	join departments using(dept_no)
where to_date = '9999-01-01'
order by first_name, last_name;

-- 문제4. 전체 사원의 사번, 이름,      연봉, 직책, 부서를 모두 이름 순서로 출력합니다.
select e.emp_no, first_name, last_name, salary, title, dept_no
from employees e join titles t on e.emp_no = t.emp_no and t.to_date = '9999-01-01' 
				join dept_emp de on e.emp_no = de.emp_no and de.to_date = '9999-01-01'
                join salaries s on e.emp_no = s.emp_no and s.to_date = '9999-01-01'
order by first_name;


-- 문제5. ‘Technique Leader’의 직책으로 과거에 근무한 적이 있는 모든 사원의 사번과 이름을 출력하세요. (현재 ‘Technique Leader’의 직책(으로 근무하는사원은 고려하지 않습니다.) 이름은 first_name과 last_name을 합쳐 출력합니다.
select e.emp_no, concat(first_name, ' ', last_name) as 이름
from employees e join titles t on e.emp_no = t.emp_no
where title = 'Technique Leader' and to_date != '9999-01-01';

-- 문제6. 직원 이름(last_name) 중에서 S(대문자)로 시작하는 직원들의 이름, 부서명, 직책을 조회하세요.
-- select emp_no, first_name, last_name from employees where last_name like 'S%'
select count(*)
from employees e join dept_emp d on e.emp_no = d.emp_no
					join dept_emp de on e.emp_no = de.emp_no
where last_name like 'S%'; 
-- 문제7. 현재, 직책이 Engineer인 사원 중에서 현재 급여가 40000 이상인 사원을 급여가 큰 순서대로 출력하세요.
select *
from employees e join titles t on e.emp_no = t.emp_no
				join salaries s on e.emp_no = s.emp_no
where t.to_date = '9999-01-01' and title = 'Engineer' and s.to_date = '9999-01-01' and salary > 40000
order by salary desc;

-- 문제8. 현재 급여가 50000이 넘는 직책을 직책, 급여로 급여가 큰 순서대로 출력하시오
select title, avg(salary)
from employees e join titles t on e.emp_no = t.emp_no
				join salaries s on e.emp_no = s.emp_no
where t.to_date = '9999-01-01' and s.to_date = '9999-01-01'
group by title
having avg(salary); 


-- 문제9. 현재, 부서별 평균 연봉을 연봉이 큰 부서 순서대로 출력하세요.
select de.dept_no, avg(salary)
from dept_emp de join salaries s on de.emp_no = s.emp_no
where de.to_date = '9999-01-01'
group by de.dept_no
order by avg(salary);
-- 문제10. 현재, 직책별 평균 연봉을 연봉이 큰 직책 순서대로 출력하세요.
select title, avg(salary) 
from titles t join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01' and s.to_date = '9999-01-01'
group by title
order by avg(salary) desc;
