-- select 연습
select * from departments;
select dept_no, dept_name from departments;

-- alias (as 생략가능)
-- 예제1: employees 테이블에서 직원의 이름, 성별, 입사일을 출력
select first_name as 이름, gender as 성별, hire_date as 입사일
from employees;

-- 예제2: 예제1의 출력을 concat을 이용해서 가공
select concat(first_name,' ', last_name) as '전체 이름',
		gender as 성별,
        hire_date as 입사일
from employees;

-- 예제3: (distinct 중복 값 제외) title 테이블에서 모든 직급의 이름을 출력
select distinct title from titles;

-- where절 #1
-- 예제: 1991년 이전에 입사한 직원의 이름, 성별, 입사일을 출력해라
select concat(first_name, ' ', last_name), gender, hire_date
from employees
where hire_date < '1991-01-01';

-- where절 #3 : in 연산자
select emp_no, dept_no
from dept_emp
where dept_no in ('d005', 'd009');

-- where 절 #4 : LIKE 검색
select first_name, hire_date
from employees
where hire_date LIKE '1989%';

-- order by절 #1
-- 예제1: 남자 직원의 이름, 성별, 입사일을 입사일 순서로 출력
select first_name, gender, hire_date
from employees
where gender = 'm'
order by hire_date;

-- 예제2: 직원들의 사번, 월급을 사번, 월급 순으로 출력
select emp_no, salary
from salaries
where to_date = '9999-01-01'
order by emp_no asc, salary desc;

select lpad('1234',10,'-');
select rpad('1234',10,'-');

select lpad(salary, 10, '*')
from salaries;



-- trim, ltrim, rtrim
select concat('----',rtrim('   aaa bbbb   '), '----');
select concat('----',ltrim('   aaa bbbb   '), '----');
select concat('----',trim(both 'x' from 'xxxxxaaa xxx  bbbbxxx'), '----')