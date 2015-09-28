package kr.poturns.virtualpalace.input;

/**
 *
 * @author Yeonho.Kim
 */
public interface IControllerCommands {

    // * * * S U P P O R T  F L A G S * * * //
    /**
     * 스크린 터치 기능을 지원하는 INPUT TYPE 을 의미한다.
     */
    int TYPE_INPUT_SUPPORT_SCREENTOUCH = 0x1;
    /**
     * 스크린 포커싱 기능을 지원하는 INPUT TYPE 을 의미한다.
     */
    int TYPE_INPUT_SUPPORT_SCREENFOCUS = 0x2;
    /**
     * 모션 인식 기능을 지원하는 INPUT TYPE 을 의미한다.
     */
    int TYPE_INPUT_SUPPORT_MOTION = 0x4;
    /**
     * 음성 인식 기능을 지원하는 INPUT TYPE 을 의미한다.
     */
    int TYPE_INPUT_SUPPORT_VOICE = 0x10;
    /**
     * SMART WATCH 에서 지원하는 INPUT TYPE 을 의미한다.
     */
    int TYPE_INPUT_SUPPORT_WATCH = 0x20;



    // * * * C O M M A N D S * * * //
    /**
     * INPUT_HANDLER 에서 UNITY 로 INPUT 이벤트을 동기화하는 명령 코드.
     * ( HANDLER 내부에서 자동 호출되므로 외부에선 호출하지 않아도 됨 )
     */
    int INPUT_SYNC_COMMAND = 0x10;
    /**
     * INPUT_HANDLER 로 단일 INPUT 이벤트를 전달하는 명령 코드.
     */
    int INPUT_SINGLE_COMMAND = 0x11;
    /**
     * INPUT_HANDLER 로 다수의 INPUT 이벤트들을 전달하는 명령 코드.
     */
    int INPUT_MULTI_COMMANDS = 0x12;


    /**
     *
     */
    int REQUEST_MESSAGE = 0x10;
    /**
     * UNITY 로부터 메시지를 전달받을 때 사용하는 명령 코드.
     */
    int REQUEST_MESSAGE_FROM_UNITY = 0x10;
    /**
     * UNITY 로부터 CALLBACK RESPONSE 를 요청받을 때 사용하는 명령 코드.
     */
    int REQUEST_CALLBACK_FROM_UNITY = 0x11;



    // * * * J S O N  C O M M A N D S * * * //
    /**
     * 명령 기본 포맷 : JSON Object
        {
            COMMAND : {
                CONTENTS
            },

            [COMMAND OPTION : {
                CONTENTS
            }]*,

            (response) RESULT : SUCCESS / FAIL / ERROR
        }
     */
    /*
     * 데이터 조회
        {
            *** 수정 작업 중 ***

            QUERY_RES : {
                // 검색할 필드 - 값
                // id, name, type

                ( 옵션 )
                , MATCH : exact / near (생략할 경우, exact)
                , SORT : {
                    // 필드 - asc / desc
                 }
            }

            *** *** *** ***
        }
     */

    /*
     * AR/VR/RES 데이터 조회/삽입/수정/삭제
        {
            INSERT_VR/AR/RES : {
                SET : {
                 //  삽입할 필드 - 값

                }
            }

            SELECT_VR/AR/RES : {
                SET : [
                    // 조회할 필드명
                ],
                WHERE : {
                    // EQUAL : 필드 - 값

                }
                WHERE_NOT : {
                    // NOT_EQUAL : 필드 - 값

                }
                WHERE_GREATER : {
                    // GREATER : 필드 - 값

                    ALLOW_EQUAL : true / false
                }
                WHERE_SMALLER : {
                    // SMALLER : 필드 - 값

                    ALLOW_EQUAL : true / false
                }
                WHERE_FROM : {
                    // BETWEEN, FROM : 필드 - 값
                    // 반드시 WHERE_TO 와 함께 기술되어야 함.

                 }
                 WHERE_TO : {
                    // BETWEEN, TO : 필드 - 값
                    // 반드시 WHERE_FROM 과 함께 기술되어야 함.

                 }
                 WHERE_LIKE : {
                    // LIKE : 필드 - 값

                 }
            }

            UPDATE_VR/AR/RES : {
                SET : {
                    //  수정할 필드 - 값

                },
                WHERE : {
                    // 수정할 데이터의 조건 : EQUAL : 필드 - 값

                }
                WHERE_NOT : {
                    // 수정할 데이터의 조건 : NOT_EQUAL : 필드 - 값

                }
                WHERE_GREATER : {
                    // GREATER : 필드 - 값

                    ALLOW_EQUAL : true / false (생략할 경우 false)
                }
                WHERE_SMALLER : {
                    // SMALLER : 필드 - 값

                    ALLOW_EQUAL : true / false  (생략할 경우 false)
                }
                WHERE_FROM : {
                    // 수정할 데이터의 조건 : BETWEEN, FROM : 필드 - 값
                    // 반드시 WHERE_TO 와 함께 기술되어야 함.

                 }
                 WHERE_TO : {
                    // 수정할 데이터의 조건 : BETWEEN, TO : 필드 - 값
                    // 반드시 WHERE_FROM 과 함께 기술되어야 함.

                 }
                 WHERE_LIKE : {
                    // 수정할 데이터의 조건 : LIKE : 필드 - 값

                 }
            }

            DELETE_VR/AR/RES : {
                // 삭제할 데이터의 조건 : 필드 - 값
                WHERE : {
                    // EQUAL : 필드 - 값

                }
                WHERE_NOT : {
                    // NOT_EQUAL : 필드 - 값

                }
                WHERE_GREATER : {
                    // GREATER : 필드 - 값

                    ALLOW_EQUAL : true / false
                }
                WHERE_SMALLER : {
                    // SMALLER : 필드 - 값

                    ALLOW_EQUAL : true / false
                }
                WHERE_FROM : {
                    // BETWEEN, FROM : 필드 - 값
                    // 반드시 WHERE_TO 와 함께 기술되어야 함.

                 }
                 WHERE_TO : {
                    // BETWEEN, TO : 필드 - 값
                    // 반드시 WHERE_FROM 과 함께 기술되어야 함.

                 }
                 WHERE_LIKE : {
                    // LIKE : 필드 - 값

                 }

            }
        }
     */
    /*
     * 상태 알림
        {
            SWITCH_PLAY_MODE : < PLAY MODE VALUE : 양수일 경우 Cardboard On / 음수일 경우 OFF>

            ACTIVATE_INPUT : < INPUT MODULE VALUE >

            DEACTIVATE_INPUT : < INPUT MODULE VALUE >


        }
     */
    // * * * J S O N  C O M M A N D S  K E Y S * * * //
    interface JsonKey {
        /**
         * 현재 위치에서 일정 범위 내 존재하는 데이터를 찾는다.
         */
        String QUERY_NEAR_ITEMS = "query_near_items";
        /**
         * VR 아이템에 렌더링할 아이템 데이터를 찾는다.
         */
        String QUERY_ALL_VR_ITEMS = "query_all_vr_items";

        /**
         * (응답 반환시) 결과 상태 KEY.
         *
         * Ex)
         * {
         *     "result" : "success / fail / error"
         * }
         */
        String RESULT = "result";
        /**
         * (응답 반환시) 쿼리 결과 KEY
         *
         * Ex)
         * {
         *     "query_result" : [
         *          { "_id" : 1, "res_id" : 1, .... },
         *          { "_id" : 2, "res_id" : 2, .... },
         *     ]
         * }
         */
        String QUERY_RESULT = "query_result";

        /**
         * VR 테이블 조회 명령.
         */
        String SELECT_VR = "select_vr";
        /**
         * AR 테이블 조회 명령.
         */
        String SELECT_AR = "select_ar";
        /**
         * Resource 테이블 조회 명령.
         */
        String SELECT_RES = "select_res";
        /**
         * VR 테이블 데이터 삽입 명령.
         */
        String INSERT_VR = "insert_vr";
        /**
         * AR 테이블 데이터 삽입 명령.
         */
        String INSERT_AR = "insert_ar";
        /**
         * Resource 테이블 데이터 삽입 명령.
         */
        String INSERT_RES = "insert_res";
        /**
         * VR 테이블 데이터 수정 명령.
         */
        String UPDATE_VR = "update_vr";
        /**
         * AR 테이블 데이터 수정 명령.
         */
        String UPDATE_AR = "update_ar";
        /**
         * Resource 테이블 데이터 수정 명령.
         */
        String UPDATE_RES = "update_res";
        /**
         * VR 테이블 데이터 삭제 명령.
         */
        String DELETE_VR = "delete_vr";
        /**
         * AR 테이블 데이터 삭제 명령.
         */
        String DELETE_AR = "delete_ar";
        /**
         * Resource 테이블 데이터 삭제 명령.
         */
        String DELETE_RES = "delete_res";


        // *** 단일 수행 명령 *** //
        /**
         * Virtual Palace 애플리케이션의 구동 모드(VR/AR/MENU) 변경 명령.
         */
        String SWITCH_PLAY_MODE = "switch_play_mode";
        /**
         * 해당 INPUT 모듈을 활성화하는 명령.
         *
         * Ex)
         * {
         *     "activate_input" : Input SupportType (int)
         * }
         */
        String ACTIVATE_INPUT = "activate_input";
        /**
         * 해당 INPUT 모듈을 비활성화하는 명령.
         *
         * Ex)
         * {
         *     "deactivate_input" : Input SupportType (int)
         * }
         */
        String DEACTIVATE_INPUT = "deactivate_input";


        // *** LocalDatabaseCenter.WriteBuilder 조건 Clause Keyword *** //
        /**
         * 조회/삽입/수정 시 target 데이터를 할당한다.
         *
         * Ex )
         *      SELECT name, type, description FROM ...
         *      {
         *          "set" : [
         *              "name",
         *              "type",
         *              "description"
         *          ]
         *      }
         *
         *      INSERT INTO resource VALUES( v1, v2, v3 ... )
         *      {
         *          "set" : {
         *              "name" : v1,
         *              "type" : v2,
         *              "description" : v3
         *          }
         *      }
         *
         *      UPDATE resource SET name=v1, type=v2, desciption=v3 WHERE ....
         *      {
         *          "set" : {
         *              "name" : v1,
         *              "type" : v2,
         *              "description" : v3
         *          }
         *      }
         */
        String SET = "set";
        /**
         * 필드-값이 일치하는 조건문을 만드는 명령.
         *
         * Ex) Select ~~ WHERE name=v1
         *      {
         *          "where" : {
         *              "name" : v1
         *          }
         *      }
         */
        String WHERE = "where";
        /**
         * 필드-값이 일치하지 않는 조건문을 만드는 명령.
         *
         * Ex) SELECT ~~ WHERE name!=v1
         *      {
         *          "where_not" : {
         *              "name" : v1
         *          }
         *      }
         */
        String WHERE_NOT = "where_not";
        /**
         * 필드-값보다 큰 경우의 조건문을 만드는 명령.
         *
         * Ex) SELECT ~~ WHERE name>v1
         *      {
         *          "where_greater" : {
         *              "name" : v1
         *          }
         *      }
         */
        String WHERE_GREATER = "where_greater";
        /**
         * 필드-값보다 작은 경우의 조건문을 만드는 명령.
         *
         * Ex) SELECT ~~ WHERE name<v1
         * {
         *      "where_smaller" : {
         *          "name" : v1
         *      }
         * }
         */
        String WHERE_SMALLER = "where_smaller";
        /**
         * 조건문에서 대/소 비교를 수행할 때, 같은 값도 포함할 것인지 여부를 정한다.
         *
         * Ex) SELECT ~~ WHERE name >= v1
         * {
         *    "where_greater" : {
         *        "name" : v1
         *        "allow_equal" : true
         *    }
         * }
         *
         */
        String ALLOW_EQUAL = "allow_equal";
        /**
         * BETWEEN 조건을 수행할 때, 시작 값을 설정하는 명령.
         *
         * Ex ) SELECT ~~ WHERE BETWEEN latitude 123.456 AND 123.567
         * {
         *     "where_from" : {
         *         "latitude" : "123.456"
         *     },
         *     "where_to" : {
         *         "latitude" : "123.567"
         *     }
         *
         * }
         */
        String WHERE_FROM = "where_from";
        /**
         * BETWEEN 조건을 수행할 때, 끝 값을 설정하는 명령.
         *
         * Ex ) SELECT ~~ WHERE BETWEEN latitude 123.456 AND 123.567
         * {
         *     "where_from" : {
         *         "latitude" : "123.456"
         *     },
         *     "where_to" : {
         *         "latitude" : "123.567"
         *     }
         *
         * }
         */
        String WHERE_TO = "where_to";
        /**
         * 필드-값이 유사한 조건문을 만드는 명령.
         *
         * Ex) SELECT ~~ WHERE name like v1
         *      {
         *          "where_like" : {
         *              "name" : v1
         *          }
         *      }
         */
        String WHERE_LIKE = "where_like";
    }
}
