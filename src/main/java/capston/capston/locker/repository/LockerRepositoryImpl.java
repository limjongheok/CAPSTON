package capston.capston.locker.repository;

import capston.capston.locker.dto.lockerUnassignedDTO.LockerUnassignedRequestDTO;
import capston.capston.locker.model.Locker;
import capston.capston.locker.model.QLocker;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.util.StringUtils;

import java.util.List;

public class LockerRepositoryImpl implements LockerRepositoryCustom{

    private  final JPAQueryFactory jpaQueryFactory;

    public LockerRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    QLocker locker = QLocker.locker;

    private BooleanExpression NotAssignLocker(int buildingNum) {

        if(StringUtils.isEmpty(buildingNum)){
            return null;
        }
        return locker.checkAssign.isFalse().and(locker.buildingNum.eq(buildingNum));

    }

    @Override
    public List<Locker> getNotAssignLocker(int buildingNum) {
        return (List<Locker>) jpaQueryFactory.from(locker).where(NotAssignLocker(buildingNum)).fetch();
    }
}
