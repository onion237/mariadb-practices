-- 문제1. 현재 평균 연봉보다 많은 월급을 받는 직원은 몇 명이나 있습니까? 
select count(*)
from salaries
where to_date = '9999-01-01' 
	and salary > (select avg(salary)
					from salaries
					where to_date = '9999-01-01');
                    
-- 문제2. 현재, 각 부서별로 최고의 급여를 받는 사원의 사번, 이름, 부서 연봉을 조회하세요. 단 조회결과는 연봉의 내림차순으로 정렬되어 나타나야 합니다.  
select e.emp_no, first_name, de.dept_no, salary
from employees e 
		join dept_emp de on e.emp_no = de.emp_no
		join salaries s on e.emp_no = s.emp_no
where (de.dept_no, salary) in
			(select de.dept_no, max(salary)
			from salaries s join dept_emp de on s.emp_no = de.emp_no
			where s.to_date = '9999-01-01'
				and de.to_date = '9999-01-01'
			group by de.dept_no) 
	and de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
order by salary desc;

-- 문제3. 현재, 자신의 부서 평균 급여보다 연봉(salary)이 많은 사원의 사번, 이름과 연봉을 조회하세요  

select e.emp_no, first_name, salary
from employees e 
	join dept_emp de on e.emp_no = de.emp_no
    join (select dept_no, avg(salary) as dept_avg_sal
		from salaries s join dept_emp de on s.emp_no = de.emp_no
		where s.to_date = '9999-01-01'
			and de.to_date = '9999-01-01'
		group by dept_no) avg_sal on de.dept_no = avg_sal.dept_no
	join salaries s on e.emp_no = s.emp_no and salary >= avg_sal.dept_avg_sal
where de.to_date = '9999-01-01' and s.to_date = '9999-01-01'
order by salary;

 select * from dept_manager;
-- 문제4. 현재, 사원들의 사번, 이름, 매니저 이름, 부서 이름으로 출력해 보세요. 
select e.emp_no, e.first_name, m.first_name as manager, dept_name 
from employees e 
	join dept_emp de on e.emp_no = de.emp_no
	join dept_manager dm on de.dept_no = dm.dept_no
    join employees m on dm.emp_no = m.emp_no
    join departments d on dm.dept_no = d.dept_no
where de.to_date = '9999-01-01'
	and dm.to_date = '9999-01-01';
 

-- 문제5. 현재, 평균연봉이 가장 높은 부서의 사원들의 사번, 이름, 직책, 연봉을 조회하고 연봉 순으로 출력하세요. 

-- limit절 활용
select e.emp_no, first_name, title, salary
from employees e 
	join dept_emp de using(emp_no)
 	join titles t using(emp_no)
    join salaries s using(emp_no)
where de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
    and t.to_date = '9999-01-01'
    and de.dept_no =
			(select dept_no
			from dept_emp de
				join salaries s on de.emp_no = s.emp_no
			where de.to_date = '9999-01-01'
				and s.to_date = '9999-01-01'
			group by dept_no
			order by avg(salary) desc
			limit 1)
order by salary desc;

-- 서브쿼리
select e.emp_no, first_name, title, salary
from employees e 
	join dept_emp de on e.emp_no = de.emp_no
 	join titles t on e.emp_no = t.emp_no
    join salaries s on e.emp_no = s.emp_no
where de.to_date = '9999-01-01'
	and t.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
    and de.dept_no =
			(select dept_no
			from dept_emp de
				join salaries s on de.emp_no = s.emp_no
			where de.to_date = '9999-01-01'
				and s.to_date = '9999-01-01'
			group by dept_no
			having avg(salary) = (select max(a.avg_sal)
									from (select avg(salary) as avg_sal
											from dept_emp de
												join salaries s on de.emp_no = s.emp_no
											where de.to_date = '9999-01-01'
												and s.to_date = '9999-01-01'
											group by dept_no) a))
order by salary desc;



-- 문제6. 평균 연봉이 가장 높은 부서는?  

-- limit
select d.dept_no, d.dept_name
from dept_emp de
	join salaries s on de.emp_no = s.emp_no
    join departments d on de.dept_no = d.dept_no
where de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by dept_no
order by avg(salary) desc
limit 1;

-- only subquery
select d.dept_no, dept_name
from dept_emp de
	join salaries s on de.emp_no = s.emp_no
    join departments d on de.dept_no = d.dept_no
where de.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by dept_no 
having avg(salary) = (select max(a.avg_sal)
						from (select dept_no, avg(salary) as avg_sal
								from dept_emp de
									join salaries s on de.emp_no = s.emp_no
								where de.to_date = '9999-01-01'
									and s.to_date = '9999-01-01'
								group by dept_no) a);
                                


-- 문제7. 평균 연봉이 가장 높은 직책? 
select title, avg(salary)
from titles t
	join salaries s on t.emp_no = s.emp_no
where t.to_date = '9999-01-01'
	and s.to_date = '9999-01-01'
group by title
order by avg(salary) desc
limit 1;



-- 문제8. 현재 자신의 매니저보다 높은 연봉을 받고 있는 직원은? 부서이름, 사원이름, 연봉, 매니저 이름, 메니저 연봉 순으로 출력합니다.
select dept_name, e.first_name, s.salary as emp_sal, m.first_name as manager, ms.salary as manager_sal
from employees e
	join salaries s on e.emp_no = s.emp_no
    join dept_emp de on e.emp_no = de.emp_no
    join departments d on de.dept_no = d.dept_no
    join dept_manager dm on de.dept_no = dm.dept_no
    join employees m on dm.emp_no = m.emp_no
    join salaries ms on m.emp_no = ms.emp_no
where s.to_date = '9999-01-01'
	and de.to_date = '9999-01-01'
    and dm.to_date = '9999-01-01'
	and ms.to_date = '9999-01-01'
    and s.salary > (
					select salary
					from dept_emp de
						join dept_manager dm on de.dept_no = dm.dept_no
						join salaries s on dm.emp_no = s.emp_no
					where de.emp_no = e.emp_no
						and de.to_date = '9999-01-01'
						and dm.to_date = '9999-01-01'
						and s.to_date = '9999-01-01'
				);