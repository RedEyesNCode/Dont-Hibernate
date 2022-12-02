package com.leadexperience.leadexperince.respository;


import com.leadexperience.leadexperince.models.UserDataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDataRepository extends JpaRepository<UserDataModel, Long> {

    @Query("SELECT s FROM UserDataModel s WHERE s.number =:numberInput AND s.password=:passwordInput")
    Optional<UserDataModel> loginUser(String numberInput, String passwordInput);

    @Query("SELECT s FROM UserDataModel s WHERE s.number = :number")
    Optional<UserDataModel> findByNumber(Long number);
    @Query("SELECT s FROM UserDataModel s WHERE s.number =:number AND s.password=:password")
    Optional<UserDataModel> loginUserData(String number, String password);

    @Query("SELECT s FROM UserDataModel s WHERE s.number = ?1")
    Optional<UserDataModel> findbyNumber(String Number);
}
