package jp.gtf.taotao001.service;

import java.util.Date;

import jp.gtf.taotao001.entity.UserEntity;
import jp.gtf.taotao001.repository.UserRepository;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

	@Autowired
	UserRepository userRep;

	/**
	 * id自動採番
	 *
	 * @return
	 */
	public String getSeqId() {
		Long seqNum = userRep.getNextSeriesId();
		String id = StringUtils.leftPad(String.valueOf(seqNum), 5, "0");
		System.out.println(" ID自動採番:" + id);
		return id;
	}

	// public String getEntrydate() {
	// // 現在日時を取得
	// Date date = new Date();
	// // 上記、現在日時のフォーマットを指定
	// DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	// String entrydate = dateFormat.format(date);
	// System.out.println(entrydate);
	// return entrydate;
	// }
	/**
	 * 入力したデータはDBに保存
	 *
	 * @param name
	 * @param email
	 * @param password
	 * @return
	 */
	public UserRepository registerUser(String name, String email,
			String password) {
		UserEntity user = new UserEntity();
		user.setId(getSeqId());
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		Date entrydate = new Date();
		user.setEntrydate(entrydate);
		// データベースに保存
		userRep.save(user);
		// System.out.println(name);
		// System.out.println(email);
		// System.out.println(password);
		return userRep;
	}

	/**
	 *
	 */

//	public Optional<UserEntity> selectFindId(String email) {
//
//		ProcessResult<UserRegisterResult> result = new ProcessResult<UserRegisterResult>();
//		Optional<UserEntity> entity = userRep.findById(email);
//		// レコードが既に存在する場合
//		if (entity.isPresent()) {
//			result.setStatus(Status.ERR_RECORD_OVERLAPPED);
//
//		}
//		return entity;
//	}
}
