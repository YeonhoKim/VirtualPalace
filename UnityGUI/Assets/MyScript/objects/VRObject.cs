﻿using System;
using System.Collections.Generic;
using BridgeApi.Controller;
using UnityEngine;
using BridgeApi.Controller.Request.Database;

namespace MyScript.objects
{
    public class VRObject : IDatabaseObject
    {
        /// <summary>
        /// VR_Table에서의 id
        /// </summary>
        public int ID { get; protected set; }


        public string Name { get; protected set; }
        public string ParentName { get; protected set; }

        public int ModelType { get; protected set; }

        public float PosX { get; protected set; }
        public float PosY { get; protected set; }
        public float PosZ { get; protected set; }

        public float RotateX { get; protected set; }
        public float RotateY { get; protected set; }
        public float RotateZ { get; protected set; }
        public float RotateW { get; protected set; }

        public float SizeX { get; protected set; }
        public float SizeY { get; protected set; }
        public float SizeZ { get; protected set; }

        public int ResID { get; protected set; }
        public string ResContents { get; protected set; }
        public string ResTitle { get; protected set; }

        public KIND_SOURCE SourceKind { get { return (KIND_SOURCE)ResID; } }
        public OBJ_LIST ObjKind { get { return (OBJ_LIST)ModelType; } }

        public Vector3 Position { get { return new Vector3(PosX, PosY, PosZ); } }
        public Quaternion Rotation { get { return new Quaternion(RotateX, RotateY, RotateZ, RotateW); } }
        public Vector3 Scale { get { return new Vector3(SizeX, SizeY, SizeZ); } }

        private VRObject()
        {
        }

        public class Builder
        {
            private VRObject vrObject;

            public Builder(string name, KIND_SOURCE sourceKind, OBJ_LIST objectKind)
            {
                vrObject = new VRObject();
                vrObject.ID = -1;

                vrObject.Name = name;
                vrObject.ModelType = (int)objectKind;

                vrObject.ResID = (int)sourceKind;
            }

            public Builder SetID(int id)
            {
                vrObject.ID = id;
                return this;
            }

            public Builder SetParentName(string name)
            {
                vrObject.ParentName = name;
                return this;
            }

            public Builder SetPosX(float f)
            {
                vrObject.PosX = f;
                return this;
            }
            public Builder SetPosY(float f)
            {
                vrObject.PosY = f;
                return this;
            }

            public Builder SetPosZ(float f)
            {
                vrObject.PosZ = f;
                return this;
            }

            public Builder SetPosition(Vector3 vector)
            {
                return SetPosX(vector.x)
                    .SetPosY(vector.y)
                    .SetPosZ(vector.z);
            }

            public Builder SetRotateX(float f)
            {
                vrObject.RotateX = f;
                return this;
            }
            public Builder SetRotateY(float f)
            {
                vrObject.RotateY = f;
                return this;
            }
            public Builder SetRotateZ(float f)
            {
                vrObject.RotateZ = f;
                return this;
            }
            public Builder SetRotateW(float f)
            {
                vrObject.RotateW = f;
                return this;
            }


            public Builder SetRotation(Quaternion rotate)
            {
                return SetRotateX(rotate.x)
                    .SetRotateY(rotate.y)
                    .SetRotateZ(rotate.z)
                    .SetRotateW(rotate.w);
            }

            public Builder SetSizeX(float f)
            {
                vrObject.SizeX = f;
                return this;
            }
            public Builder SetSizeY(float f)
            {
                vrObject.SizeX = f;
                return this;
            }
            public Builder SetSizeZ(float f)
            {
                vrObject.SizeX = f;
                return this;
            }

            public Builder SetScale(Vector3 vector)
            {
                return SetSizeX(vector.x)
                    .SetSizeY(vector.y)
                    .SetSizeZ(vector.z);
            }


            public Builder SetResTitle(string s)
            {
                vrObject.ResTitle = s;
                return this;
            }

            public Builder SetResContents(string s)
            {
                vrObject.ResContents = s;
                return this;
            }

            public VRObject Build()
            {
                return vrObject;
            }
        }

        public KeyValuePair<Enum, string>[] ConvertToPairs()
        {
            KeyValuePair<Enum, string>[] pairs = new KeyValuePair<Enum, string>[16];
            int i = 0;

            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.NAME, Name);
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.PARENT_NAME, ParentName);
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.MODEL_TYPE, ModelType.ToString());

            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.POS_X, PosX.ToString());
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.POS_Y, PosY.ToString());
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.POS_Z, PosZ.ToString());

            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.ROTATE_X, RotateX.ToString());
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.ROTATE_Y, RotateY.ToString());
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.ROTATE_Z, RotateZ.ToString());
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.ROTATE_W, RotateW.ToString());

            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.SIZE_X, SizeX.ToString());
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.SIZE_Y, SizeY.ToString());
            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.SIZE_Z, SizeZ.ToString());

            pairs[i++] = new KeyValuePair<Enum, string>(VIRTUAL_FIELD.RES_ID, ResID.ToString());
            pairs[i++] = new KeyValuePair<Enum, string>(RESOURCE_FIELD.TITLE, ResTitle);
            pairs[i++] = new KeyValuePair<Enum, string>(RESOURCE_FIELD.CONTENTS, ResContents);

            return pairs;
        }

        public static VRObject FromJSON(LitJson.JsonData jsonData)
        {
            //Debug.Log("===== " + jsonData.ToJson());

            VRObject data = new VRObject();

            data.ID = int.Parse((string)jsonData[VIRTUAL_FIELD._ID.ToString()]);

            data.Name = (string)jsonData[VIRTUAL_FIELD.NAME.ToString()];
            data.ParentName = (string)jsonData[VIRTUAL_FIELD.PARENT_NAME.ToString()];
            data.ModelType = int.Parse((string)jsonData[VIRTUAL_FIELD.MODEL_TYPE.ToString()]);

            data.PosX = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.POS_X.ToString());
            data.PosY = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.POS_Y.ToString());
            data.PosZ = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.POS_Z.ToString());

            data.RotateX= JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.ROTATE_X.ToString());
            data.RotateY = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.ROTATE_Y.ToString());
            data.RotateZ = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.ROTATE_Z.ToString());
            data.RotateW = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.ROTATE_W.ToString());

            data.SizeX = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.SIZE_X.ToString());
            data.SizeY = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.SIZE_Y.ToString());
            data.SizeZ = JsonInterpreter.ParseFloatData(jsonData, VIRTUAL_FIELD.SIZE_Z.ToString());

            data.ResID = int.Parse((string)jsonData[VIRTUAL_FIELD.RES_ID.ToString()]);
            data.ResTitle = (string)jsonData[RESOURCE_FIELD.TITLE.ToString()];
            data.ResContents = (string)jsonData[RESOURCE_FIELD.CONTENTS.ToString()];

            return data;
        }
    }
}