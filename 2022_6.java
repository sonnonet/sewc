        myDBHelper = new myDBHelper(this);
        btnInit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                myDBHelper.onUpgrade(sqlDB,1,2);
                sqlDB.close();
            }
        });
        btnInsert.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getWritableDatabase();
                sqlDB.execSQL("INSERT INTO user1 VALUES ( '" + edtName.getText().toString() + "' , '" + edtPhoneNumber.getText().toString() + "' ,  '" + edtEmail.getText().toString() + "', " + edtSecure.getText().toString() + " , '" + edtAddress.getText().toString() + "' )");
                sqlDB.close();
                Toast.makeText(getApplicationContext(),"입력됨",0).show();
            }
        });

        btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqlDB = myDBHelper.getReadableDatabase();
                Cursor cursor;
                cursor = sqlDB.rawQuery("SELECT * FROM user1;",null);

                String strNames = "이름"+"\r\n"+"\r\n";
                String strPhoneNumbers = "핸드폰번호"+"\r\n"+"\r\n";
                String strEmail = "이메일 주소"+"\r\n"+"\r\n";
                String strSecure = "비밀번호"+"\r\n"+"\r\n";
                String strAddress = "집 주소"+"\r\n"+"\r\n";

                while (cursor.moveToNext()){
                    strNames += cursor.getString(0) + "\r\n";
                    strPhoneNumbers += cursor.getString(1) + "\r\n";
                    strEmail += cursor.getString(2) + "\r\n";
                    strSecure += cursor.getString(3) + "\r\n";
                    strAddress += cursor.getString(4) + "\r\n";

                }
                edtNameResult.setText(strNames);
                edtPhoneNumberResult.setText(strPhoneNumbers);
                edtEmailResult.setText(strEmail);
                edtSecureResult.setText(strSecure);
                edtAddressResult.setText(strAddress);
                cursor.close();
                sqlDB.close();
            }
        });
